package com.IBMInternship.ticket_service.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDTO {


    private Long id;
    private String message;
    private String authorEmail;

    private Long ticketId;
    private LocalDateTime createdAt;
    private Long parrentCommentId;


}