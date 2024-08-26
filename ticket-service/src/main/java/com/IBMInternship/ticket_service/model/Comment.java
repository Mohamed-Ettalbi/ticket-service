package com.IBMInternship.ticket_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {




        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long commentId;


        private String message;


        private LocalDateTime createdAt;

        @ManyToOne
        @JoinColumn(name = "ticket_id")
        private Ticket ticket;

        private Long userId;


    }

