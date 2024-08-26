package com.IBMInternship.ticket_service.model;


import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Attachment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long attachmentId;

    private String type;
    private String url;

    private LocalDateTime createdAt;

    private LocalDateTime deletedAt;

    @ManyToOne
    @JoinColumn(name = "ticket_id")
    private Ticket ticket;
}
