package com.IBMInternship.ticket_service.service;

import com.IBMInternship.ticket_service.repositories.TicketCategoryRepository;
import com.IBMInternship.ticket_service.repositories.TicketRepository;
import com.IBMInternship.ticket_service.model.dtos.CreateTicketDTO;
import com.IBMInternship.ticket_service.model.dtos.TicketDTO;
import com.IBMInternship.ticket_service.model.dtos.UpdateTicketDTO;
import com.IBMInternship.ticket_service.mappers.TicketMapper;
import com.IBMInternship.ticket_service.model.entities.TicketEntity;
import com.IBMInternship.ticket_service.model.entities.TicketCategoryEntity;
import com.IBMInternship.ticket_service.model.enumerations.TicketStatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private TicketCategoryRepository ticketCategoryRepository;


    private TicketEntity preapareCreateTicketData(TicketEntity convertedTicketEntity , Long categoryId){

        TicketCategoryEntity ticketCategoryEntity = ticketCategoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Ticket category not found"));

            convertedTicketEntity.setTicketCategoryEntity(ticketCategoryEntity);
            convertedTicketEntity.setCreatedAt(LocalDateTime.now());
            convertedTicketEntity.setUpdatedAt(LocalDateTime.now());
            convertedTicketEntity.setCreatedBy(1L);// to be removed later
            convertedTicketEntity.setStatus(TicketStatusEnum.OPEN);
            return convertedTicketEntity;
    }



    public TicketDTO createTicket(CreateTicketDTO ticket) {
        TicketEntity convertedTicketEntity = TicketMapper.toEntityForCreate(ticket);
        TicketEntity  CompleteConvertedTicketEntity =  preapareCreateTicketData(convertedTicketEntity , ticket.getTicketCategory());
        ticketRepository.save(CompleteConvertedTicketEntity);
        return TicketMapper.toDTO(CompleteConvertedTicketEntity);
             }



    public TicketDTO getTicketById(Long id) {

        TicketEntity ticketEntity = ticketRepository.findById(id)
                .orElseThrow(()->
                        new RuntimeException("no ticket by the id : " +id));//to add exceptions later on
        return TicketMapper.toDTO(ticketEntity);
    }



    public List<TicketDTO> getAllTickets() {

       List<TicketEntity>  ticketsList = ticketRepository.findAll();
       return TicketMapper.toDTOs(ticketsList);
    }



    public TicketDTO updateTicketPartially(Long id, UpdateTicketDTO update ) {
        TicketEntity ticketEntity = ticketRepository.findById(id)
                .orElseThrow(()->
                        new RuntimeException("UPDATE ERROR     :    no ticket by the id  : " +id));


        //update Logic
            ticketEntity.setUpdatedAt(LocalDateTime.now());
            ticketEntity.setUpdatedBy(1L);//for now when i do the logic for createdAt ill use the same method for this
            ticketEntity.setTitle(update.getTitle());
            ticketEntity.setDescription(update.getDescription());
            ticketEntity.setPriority(update.getPriority());
            TicketCategoryEntity ticketCategoryEntity = ticketCategoryRepository.findById(update.getTicketCategory())
                    .orElseThrow(() -> new RuntimeException("Ticket category not found"));
            ticketEntity.setTicketCategoryEntity(ticketCategoryEntity);


        ticketRepository.save(ticketEntity);
        return TicketMapper.toDTO(ticketEntity);
    }

    public void deleteTicket(Long id) {
        if (ticketRepository.existsById(id)) {
        ticketRepository.deleteById(id);
        }else {
            throw new RuntimeException("no ticket by the id : " +id);
        }
    }
}

