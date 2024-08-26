package com.IBMInternship.ticket_service.dao.dto;

import com.IBMInternship.ticket_service.model.TicketPriority;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateTicketDTO {

        private String title;
        private String description;
        private TicketPriority priority;
        private Long TicketCategory;


        // Getters and Setters

}
