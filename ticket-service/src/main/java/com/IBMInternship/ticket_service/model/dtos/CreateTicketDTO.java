package com.IBMInternship.ticket_service.model.dtos;

import com.IBMInternship.ticket_service.model.enumerations.TicketPriorityEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateTicketDTO {

        private String title;

        private String description;
        private TicketPriorityEnum priority;
        private Long TicketCategory;
        // Getters and Setters

}
