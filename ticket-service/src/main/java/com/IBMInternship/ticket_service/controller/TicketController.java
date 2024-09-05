package com.IBMInternship.ticket_service.controller;

import com.IBMInternship.ticket_service.model.dtos.CreateTicketDTO;
import com.IBMInternship.ticket_service.model.dtos.TicketDTO;
import com.IBMInternship.ticket_service.model.dtos.UpdateTicketDTO;
import com.IBMInternship.ticket_service.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
        @RequestMapping("/api/tickets")
        public class TicketController {

    @Autowired
    private TicketService ticketService;

    @PostMapping("/{createdBy}")
    public ResponseEntity<TicketDTO> createTicket( @PathVariable String createdBy ,@RequestBody CreateTicketDTO createTicketDTO ) {
        TicketDTO createdTicket = ticketService.createTicket(createTicketDTO, createdBy);
        return ResponseEntity.ok(createdTicket);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TicketDTO> getTicketById(@PathVariable Long id) {
        TicketDTO ticket = ticketService.getTicketById(id);
        return ResponseEntity.ok(ticket);

    }

    @GetMapping
    public ResponseEntity<List<TicketDTO>> getAllTickets() {
        List<TicketDTO> ticketsList = ticketService.getAllTickets();
        return ResponseEntity.ok(ticketsList);


    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteTicketById(@PathVariable Long id) {

        ticketService.deleteTicket(id);
        return ResponseEntity.ok("Ticket deleted");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<TicketDTO> updateTicketPartially(@PathVariable Long id, @RequestBody UpdateTicketDTO updateTicketDTO) {


        TicketDTO updatedTicket = ticketService.updateTicketPartially(id,updateTicketDTO);
        return ResponseEntity.ok(updatedTicket);
    }
}


