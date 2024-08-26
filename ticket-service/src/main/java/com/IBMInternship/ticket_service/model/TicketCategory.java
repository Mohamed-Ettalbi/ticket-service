package com.IBMInternship.ticket_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
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

