package com.IBMInternship.ticket_service.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class TicketCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false, name = "name")
    private String name;


    @Column(nullable = false, name = "description")
    private String description;


    @OneToMany(mappedBy = "ticketCategory")
    private List<Ticket> tickets;



}

