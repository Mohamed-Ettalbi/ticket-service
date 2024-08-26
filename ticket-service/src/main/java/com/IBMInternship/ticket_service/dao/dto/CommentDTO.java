package com.IBMInternship.ticket_service.dao.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDTO {


    private Long id;
    private String message;

    private Long ticketId;
    private LocalDateTime createdAt;


}
