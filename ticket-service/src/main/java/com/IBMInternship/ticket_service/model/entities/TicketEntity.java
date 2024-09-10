package com.IBMInternship.ticket_service.model.entities;

import com.IBMInternship.ticket_service.model.enumerations.TicketPriorityEnum;
import com.IBMInternship.ticket_service.model.enumerations.TicketStatusEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "tickets")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "title")
    private String title;

    @Column(nullable = false, length = 3000, name = "description")
    private String description;

    @Column(nullable = false, name = "priority")
    @Enumerated(EnumType.STRING)
    private TicketPriorityEnum priority;

 @Column(
//         nullable = false,
            name = "status")
    @Enumerated(EnumType.STRING)
    private TicketStatusEnum status;

    @Column(nullable = false, name = "createdBy")
    private String createdBy;

    @Column
    private Long updatedBy;

    @Column(nullable = false, name = "createdAt")
    private LocalDateTime createdAt;

    @Column(nullable = false, name = "updatedAt")
    private LocalDateTime  updatedAt;


    private LocalDateTime resolvedAt;


    @Column(name = "assigned_to")
    private String assignedTo;          //the technician handling the ticket
    @Column(name = "assigned_group")
    private Long assignedGroup;     // the group of technicians the ticket is assigned to

    @ManyToOne
    @JoinColumn(name ="category_id")
    private TicketCategoryEntity ticketCategoryEntity;

    @OneToMany(mappedBy = "ticket", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CommentEntity> commentEntities;

    @OneToMany(mappedBy = "ticket", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AttachmentEntity> attachmentEntities;










}
