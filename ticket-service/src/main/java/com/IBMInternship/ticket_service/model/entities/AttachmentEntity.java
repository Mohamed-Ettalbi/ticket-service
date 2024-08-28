package com.IBMInternship.ticket_service.model.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data

public class AttachmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long attachmentId;

    private String fileType;
    private String fileName;

    @Lob
    private byte[] data;

    private LocalDateTime createdAt;


    @ManyToOne
    @JoinColumn(name = "ticket_id")
    private TicketEntity ticket;


    public AttachmentEntity(String fileName, String fileType, byte[] data ,
                            LocalDateTime createdAt
                            , TicketEntity ticket) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.data = data;
        this.createdAt = createdAt;
        this.ticket = ticket;
    }
    public AttachmentEntity() {}

}
