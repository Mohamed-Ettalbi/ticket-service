package com.IBMInternship.ticket_service.service;

import com.IBMInternship.ticket_service.exceptions.TicketNotFoundException;
import com.IBMInternship.ticket_service.exceptions.TicketCategoryNotFoundException;
import com.IBMInternship.ticket_service.exceptions.TechnicianNotInTicketGroup;
import com.IBMInternship.ticket_service.exceptions.UnauthorizedAccessException;
import com.IBMInternship.ticket_service.model.dtos.StatusUpdateDTO;
import com.IBMInternship.ticket_service.repositories.TicketCategoryRepository;
import com.IBMInternship.ticket_service.repositories.TicketRepository;
import com.IBMInternship.ticket_service.model.dtos.CreateTicketDTO;
import com.IBMInternship.ticket_service.model.dtos.TicketDTO;
import com.IBMInternship.ticket_service.model.dtos.UpdateTicketDTO;
import com.IBMInternship.ticket_service.mappers.TicketMapper;
import com.IBMInternship.ticket_service.model.entities.TicketEntity;
import com.IBMInternship.ticket_service.model.entities.TicketCategoryEntity;
import com.IBMInternship.ticket_service.model.enumerations.TicketStatusEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TicketService {

    @Autowired
    private  TicketRepository ticketRepository;
    @Autowired
    private  TicketCategoryRepository ticketCategoryRepository;

    private static final Logger logger = LoggerFactory.getLogger(TicketService.class);



    private TicketEntity prepareCreateTicketData(TicketEntity convertedTicketEntity, Long categoryId, String createdBy) {
        TicketCategoryEntity ticketCategoryEntity = ticketCategoryRepository.findById(categoryId)
                .orElseThrow(() -> new TicketCategoryNotFoundException("Ticket category not found"));

        convertedTicketEntity.setTicketCategoryEntity(ticketCategoryEntity);
        convertedTicketEntity.setCreatedAt(LocalDateTime.now());
        convertedTicketEntity.setUpdatedAt(LocalDateTime.now());
        convertedTicketEntity.setCreatedBy(createdBy); // Email extracted from sub: in token
        convertedTicketEntity.setStatus(TicketStatusEnum.OPEN);
        return convertedTicketEntity;
    }


    public TicketDTO createTicket(CreateTicketDTO ticket, String createdBy) {
        TicketEntity convertedTicketEntity = TicketMapper.toEntityForCreate(ticket);
        TicketEntity completeConvertedTicketEntity = prepareCreateTicketData(convertedTicketEntity, ticket.getTicketCategory(), createdBy);
        ticketRepository.save(completeConvertedTicketEntity);
        return TicketMapper.toDTO(completeConvertedTicketEntity);
    }

    public TicketDTO getTicketById(Long id) {
        TicketEntity ticketEntity = ticketRepository.findById(id)
                .orElseThrow(() -> new TicketNotFoundException("No ticket found with ID: " + id));
        return TicketMapper.toDTO(ticketEntity);
    }

    public List<TicketDTO> getAllTickets() {
        List<TicketEntity> ticketsList = ticketRepository.findAll();
        return TicketMapper.toDTOs(ticketsList);
    }


    public TicketDTO updateTicketPartially(Long id, UpdateTicketDTO update) {
        TicketEntity ticketEntity = ticketRepository.findById(id)
                .orElseThrow(() -> new TicketNotFoundException("No ticket found with ID: " + id));
        boolean ifOwner = update.getTicketOwnerEmail().equals(ticketEntity.getCreatedBy());

        if (!ifOwner) {

            throw new UnauthorizedAccessException("You Don't have the right to edit this ticket");
        }
        ticketEntity.setUpdatedAt(LocalDateTime.now());
        ticketEntity.setUpdatedBy(1L); // Replace with actual user ID logic
        ticketEntity.setTitle(update.getTitle());
        ticketEntity.setDescription(update.getDescription());
        ticketEntity.setPriority(update.getPriority());

        TicketCategoryEntity ticketCategoryEntity = ticketCategoryRepository.findById(update.getTicketCategory())
                .orElseThrow(() -> new TicketCategoryNotFoundException("Ticket category not found"));
        ticketEntity.setTicketCategoryEntity(ticketCategoryEntity);

        ticketRepository.save(ticketEntity);
        return TicketMapper.toDTO(ticketEntity);
    }
    public TicketDTO updateTicketStatus (Long ticketId, StatusUpdateDTO statusUpdateDTO ){

        TicketEntity ticketEntity = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new TicketNotFoundException("No ticket found with ID: " + ticketId));
        logger.debug("Checking the dto received {}" ,statusUpdateDTO);
        Boolean checkingGroup= statusUpdateDTO.getTechnicianGroupId() == ticketEntity.getAssignedGroup();
        boolean ifAssignedToHim = statusUpdateDTO.getStatusUpdatedBy().equals( ticketEntity.getAssignedTo());

        logger.debug(" email from backend  {}  , email from ticket   {}   result : {}" ,statusUpdateDTO.getStatusUpdatedBy() , ticketEntity.getAssignedTo(), ifAssignedToHim);
        logger.debug("Checking if assigned to him? {} if in group {}" , ifAssignedToHim , checkingGroup);
        if (statusUpdateDTO.isAdmin()){
                ticketEntity.setUpdatedAt(LocalDateTime.now());
            ticketEntity.setStatus(statusUpdateDTO.getStatus());
            if(statusUpdateDTO.getStatus() == TicketStatusEnum.CLOSED){
                ticketEntity.setResolvedAt(LocalDateTime.now());
            }
            ticketRepository.save(ticketEntity);
            return TicketMapper.toDTO(ticketEntity);
        }else if(checkingGroup && ifAssignedToHim) {

          logger.debug("technicianGroupId check Result is : {}" ,checkingGroup);


          ticketEntity.setUpdatedAt(LocalDateTime.now());
        ticketEntity.setStatus(statusUpdateDTO.getStatus());

        if(statusUpdateDTO.getStatus() == TicketStatusEnum.CLOSED){
            ticketEntity.setResolvedAt(LocalDateTime.now());
        }
        ticketRepository.save(ticketEntity);

return TicketMapper.toDTO(ticketEntity);
        } else throw new TechnicianNotInTicketGroup("this technician is not allowed to set this ticket status");
    }


    public void deleteTicket(Long id) {
        if (ticketRepository.existsById(id)) {
            ticketRepository.deleteById(id);
        } else {
            throw new TicketNotFoundException("No ticket found with ID: " + id);
        }
    }
}
