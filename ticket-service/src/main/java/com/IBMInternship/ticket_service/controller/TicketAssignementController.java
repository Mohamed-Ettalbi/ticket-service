package com.IBMInternship.ticket_service.controller;

import com.IBMInternship.ticket_service.model.dtos.TicketDTO;

import com.IBMInternship.ticket_service.service.TicketAssignementService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tickets/")
public class TicketAssignementController {

    @Autowired
    private TicketAssignementService ticketAssignmentService;




    @PutMapping("/{ticketId}/assign/user/{userEmail}/{technicianGroupId}")
    public ResponseEntity<TicketDTO> assignTicketToUser(@PathVariable Long ticketId, @PathVariable String userEmail , @PathVariable Long technicianGroupId) {
        TicketDTO updatedTicket = ticketAssignmentService.assignTicketToUser(ticketId, userEmail, technicianGroupId);
        return ResponseEntity.ok(updatedTicket);
    }

    @PutMapping("/{ticketId}/assign/group/{groupId}")
    public ResponseEntity<TicketDTO> assignTicketToGroup(@PathVariable Long ticketId, @PathVariable Long groupId) {
        TicketDTO updatedTicket = ticketAssignmentService.assignTicketToGroup(ticketId, groupId);
        return ResponseEntity.ok(updatedTicket);
    }

    @PutMapping("/{ticketId}/unassign")
    public ResponseEntity<TicketDTO> unassignTicket(@PathVariable Long ticketId) {
        TicketDTO updatedTicket = ticketAssignmentService.unassignTicket(ticketId);
        return ResponseEntity.ok(updatedTicket);
    }
}



