package com.IBMInternship.ticket_service.model.dtos;

import com.IBMInternship.ticket_service.model.enumerations.TicketPriorityEnum;
import com.IBMInternship.ticket_service.model.enumerations.TicketStatusEnum;
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
    private TicketStatusEnum status;
    private TicketPriorityEnum priority;
    private String ticketCategoryName;
    private Long assignedTo;
    private Long assignedGroup;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime resolvedAt;
    private String createdBy;

}
