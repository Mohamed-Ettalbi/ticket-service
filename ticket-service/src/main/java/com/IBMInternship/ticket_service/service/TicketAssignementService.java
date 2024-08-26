package com.IBMInternship.ticket_service.service;

import com.IBMInternship.ticket_service.dao.TicketRepository;
import com.IBMInternship.ticket_service.dao.dto.TicketDTO;
import com.IBMInternship.ticket_service.dao.mappers.TicketMapper;
import com.IBMInternship.ticket_service.model.Ticket;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketAssignementService {

    @Autowired
    private TicketRepository ticketRepository;

    @Transactional
    public TicketDTO assignTicketToUser(Long ticketId, Long userId) {
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));
        ticket.setAssignedTo(userId);
        ticketRepository.save(ticket);

        return TicketMapper.toDTO(ticket);
    }

    @Transactional
    public TicketDTO assignTicketToGroup(Long ticketId, Long groupId) {
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));

        ticket.setAssignedGroup(groupId);
        ticketRepository.save(ticket);

        return TicketMapper.toDTO(ticket);
    }

    @Transactional
    public TicketDTO unassignTicket(Long ticketId) {
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));

        ticket.setAssignedTo(null);
        ticket.setAssignedGroup(null);
        ticketRepository.save(ticket);

        return TicketMapper.toDTO(ticket);
    }


}
