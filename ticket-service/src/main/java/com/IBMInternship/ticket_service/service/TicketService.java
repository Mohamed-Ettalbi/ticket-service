package com.IBMInternship.ticket_service.service;

import com.IBMInternship.ticket_service.dao.TicketCategoryRepository;
import com.IBMInternship.ticket_service.dao.TicketRepository;
import com.IBMInternship.ticket_service.dao.dto.CreateTicketDTO;
import com.IBMInternship.ticket_service.dao.dto.TicketDTO;
import com.IBMInternship.ticket_service.dao.dto.UpdateTicketDTO;
import com.IBMInternship.ticket_service.dao.mappers.TicketMapper;
import com.IBMInternship.ticket_service.model.Ticket;
import com.IBMInternship.ticket_service.model.TicketCategory;
import com.IBMInternship.ticket_service.model.TicketStatus;
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

    public TicketDTO createTicket(CreateTicketDTO ticket) {
      Ticket  convertedTicket = TicketMapper.toEntityForCreate(ticket);

        TicketCategory ticketCategory = ticketCategoryRepository.findById(ticket.getTicketCategory())
                .orElseThrow(() -> new RuntimeException("Ticket category not found"));
      convertedTicket.setTicketCategory(ticketCategory);
      convertedTicket.setCreatedAt(LocalDateTime.now());
      convertedTicket.setUpdatedAt(LocalDateTime.now());
// to be removed later
        convertedTicket.setCreatedBy(1L);
      convertedTicket.setStatus(TicketStatus.OPEN);
      ticketRepository.save(convertedTicket);
        return TicketMapper.toDTO(convertedTicket);
             }

    public TicketDTO getTicketById(Long id) {

        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(()->
                        new RuntimeException("no ticket by the id : " +id));//to add exceptions later on
        return TicketMapper.toDTO(ticket);
    }

    public List<TicketDTO> getAllTickets() {

       List<Ticket>  ticketsList = ticketRepository.findAll();
       return TicketMapper.toDTOs(ticketsList);
    }

    public TicketDTO updateTicketPartially(Long id, UpdateTicketDTO update ) {

        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(()->
                        new RuntimeException("UPDATE ERROR     :    no ticket by the id  : " +id));


        //update Logic
        ticket.setUpdatedAt(LocalDateTime.now());
        ticket.setUpdatedBy(1L);//for now when i do the logic for createdAt ill use the same method for this
        ticket.setTitle(update.getTitle());
        ticket.setDescription(update.getDescription());
        ticket.setPriority(update.getPriority());

        TicketCategory ticketCategory = ticketCategoryRepository.findById(update.getTicketCategory())
                .orElseThrow(() -> new RuntimeException("Ticket category not found"));
        ticket.setTicketCategory(ticketCategory);


        ticketRepository.save(ticket);

        return TicketMapper.toDTO(ticket);


    }

    public void deleteTicket(Long id) {
        if (ticketRepository.existsById(id)) {
        ticketRepository.deleteById(id);
        }else {
            throw new RuntimeException("no ticket by the id : " +id);
        }
    }
}

