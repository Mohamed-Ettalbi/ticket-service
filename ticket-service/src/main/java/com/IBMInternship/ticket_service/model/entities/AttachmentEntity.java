package com.IBMInternship.ticket_service.model.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttachmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long attachmentId;

    private String fileType;
    private String fileName;

    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    private byte[] data;

    private LocalDateTime createdAt;

    private long size;


    @ManyToOne
    @JoinColumn(name = "ticket_id")
    private TicketEntity ticket;



}
