package com.IBMInternship.ticket_service.dao.dto;

import com.IBMInternship.ticket_service.model.TicketPriority;
import com.IBMInternship.ticket_service.model.TicketStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketDTO {
    private Long ticketId;
    private String title;
    private String description;
    private TicketStatus status;
    private TicketPriority priority;
    private String ticketCategoryName;
    private Long assignedTo;
    private Long assignedGroup;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime resolvedAt;

    // Getters and Setters
}
