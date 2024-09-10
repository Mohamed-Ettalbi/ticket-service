package com.IBMInternship.ticket_service.service;

import com.IBMInternship.ticket_service.exceptions.TechnicianNotInTicketGroup;
import com.IBMInternship.ticket_service.repositories.TicketRepository;
import com.IBMInternship.ticket_service.model.dtos.TicketDTO;
import com.IBMInternship.ticket_service.mappers.TicketMapper;
import com.IBMInternship.ticket_service.model.entities.TicketEntity;
import com.IBMInternship.ticket_service.exceptions.TicketNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketAssignementService {

    @Autowired
    private TicketRepository ticketRepository;

    @Transactional
    public TicketDTO assignTicketToUser(Long ticketId, String userEmail, Long technicianGroupId) {
        TicketEntity ticketEntity = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new TicketNotFoundException("Ticket not found"));
        if (technicianGroupId == ticketEntity.getAssignedGroup()) {






        ticketEntity.setAssignedTo(userEmail);
        ticketRepository.save(ticketEntity);

        return TicketMapper.toDTO(ticketEntity);
    }
        else throw new TechnicianNotInTicketGroup("technician not in ticket group");
    }

    @Transactional
    public TicketDTO assignTicketToGroup(Long ticketId, Long groupId) {
        TicketEntity ticketEntity = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new TicketNotFoundException("Ticket not found"));

        ticketEntity.setAssignedGroup(groupId);
        ticketRepository.save(ticketEntity);

        return TicketMapper.toDTO(ticketEntity);
    }

    @Transactional
    public TicketDTO unassignTicket(Long ticketId) {
        TicketEntity ticketEntity = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new TicketNotFoundException("Ticket not found"));

        ticketEntity.setAssignedTo(null);
        ticketEntity.setAssignedGroup(null);
        ticketRepository.save(ticketEntity);

        return TicketMapper.toDTO(ticketEntity);
    }
}
