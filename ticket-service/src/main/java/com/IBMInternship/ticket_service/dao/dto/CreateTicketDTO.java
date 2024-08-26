package com.IBMInternship.ticket_service.dao.dto;

import com.IBMInternship.ticket_service.model.Ticket;
import com.IBMInternship.ticket_service.model.TicketCategory;
import com.IBMInternship.ticket_service.model.TicketPriority;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateTicketDTO {

        private String title;
        private String description;
        private TicketPriority priority;
        private Long TicketCategory;


        // Getters and Setters

}
