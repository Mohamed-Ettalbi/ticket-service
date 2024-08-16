package com.IBMInternship.ticket_service.model;

import jakarta.persistence.*;
import lombok.Builder;

import java.time.LocalDateTime;

@Entity
@Table(name = "tickets")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "title")
    private String title;

    @Column(nullable = false, name = "description")
    private String description;

    @Column(nullable = false, name = "priority")
    @Enumerated(EnumType.STRING)
    private TicketPriority priority;

    @Column(nullable = false, name = "status")
    @Enumerated(EnumType.STRING)
    private TicketStatus status;

    @Column(nullable = false, name = "createdBy")
    private Long createdBy;

    @Column(nullable = false, name = "createdAt")
    private LocalDateTime createdAt;

    @Column(nullable = false, name = "updatedAt")
    private LocalDateTime  updatedAt;


    private LocalDateTime resolvedAt;


    @Column(name = "assigned_to")
    private Long assignedTo;          //the technician handling the ticket
    @Column(name = "assigned_group")
    private Long assignedGroup;     // the group of technicians the ticket is assigned to

    @ManyToOne
    @JoinColumn(name ="category_id")
    private TicketCategory ticketCategory;







}
