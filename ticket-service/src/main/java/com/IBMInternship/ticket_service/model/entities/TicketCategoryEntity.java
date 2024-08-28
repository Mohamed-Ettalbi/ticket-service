package com.IBMInternship.ticket_service.model.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class TicketCategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false, name = "name")
    private String name;


    @Column(nullable = false, name = "description")
    private String description;


    @OneToMany(mappedBy = "ticketCategoryEntity")
    private List<TicketEntity> ticketEntities;



}

