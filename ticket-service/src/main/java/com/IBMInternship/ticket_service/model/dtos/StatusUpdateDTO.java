package com.IBMInternship.ticket_service.model.dtos;

import com.IBMInternship.ticket_service.model.enumerations.TicketStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor
@AllArgsConstructor
public class StatusUpdateDTO {

    private TicketStatusEnum status;
    private String statusUpdatedBy;
    private Long technicianGroupId;
    private boolean isAdmin;


}
