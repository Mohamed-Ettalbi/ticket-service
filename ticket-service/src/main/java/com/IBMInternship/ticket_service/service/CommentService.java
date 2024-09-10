package com.IBMInternship.ticket_service.service;

import com.IBMInternship.ticket_service.exceptions.UnauthorizedAccessException;
import com.IBMInternship.ticket_service.model.dtos.CommentDTO;
import com.IBMInternship.ticket_service.repositories.CommentRepository;
import com.IBMInternship.ticket_service.repositories.TicketRepository;
import com.IBMInternship.ticket_service.mappers.CommentMapper;
import com.IBMInternship.ticket_service.model.entities.CommentEntity;
import com.IBMInternship.ticket_service.model.entities.TicketEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import com.IBMInternship.ticket_service.exceptions.CommentNotFoundException;
import com.IBMInternship.ticket_service.exceptions.TicketNotFoundException;


@Service
public class CommentService {
    private static final Logger logger = LoggerFactory.getLogger(TicketService.class);


    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private TicketRepository ticketRepository;

    public CommentDTO addCommentToTicket(Long ticketId,CommentDTO commentDTO) {
        TicketEntity ticketEntity = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new TicketNotFoundException("Ticket not found"));

        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setTicket(ticketEntity);
        commentEntity.setMessage(commentDTO.getMessage());
        commentEntity.setCommentParrentId(commentDTO.getParrentCommentId());
        commentEntity.setAuthorEmail(commentDTO.getAuthorEmail());

        commentEntity.setCreatedAt(LocalDateTime.now());

        CommentEntity savedCommentEntity = commentRepository.save(commentEntity);
        return CommentMapper.toDTO(savedCommentEntity);
    }

    public List<CommentDTO> getCommentsByTicketId(Long ticketId) {
        if (!ticketRepository.existsById(ticketId)) {
            throw new TicketNotFoundException("Ticket not found");
        }

        List<CommentEntity> commentEntities = commentRepository.findByTicketId(ticketId);

        return CommentMapper.toDTOs(commentEntities);
    }

    public void deleteCommentById(Long commentId , String email) {

        CommentEntity commentEntity = commentRepository.findById(commentId)
                .orElseThrow(() -> new CommentNotFoundException("Comment not found "));
        logger.debug("Deleting comment with id  {} and email of user deleting is {} " ,commentId , email);
String commentAuthor = commentEntity.getAuthorEmail();
   boolean emailMatchCheck= email.equals(commentAuthor);
   logger.debug("email check of commentAuthor {} and deleting user Email {} is : {}:" , commentAuthor , email ,emailMatchCheck );

        if (email != null && !email.isEmpty()) {
            if (!emailMatchCheck) {
                throw new UnauthorizedAccessException("You are not authorized to delete this comment.");
            }
        }
            commentRepository.delete(commentEntity);



    }

    public CommentDTO updateComment(Long id, String message) {
        CommentEntity commentEntity = commentRepository.findById(id)
                .orElseThrow(() -> new CommentNotFoundException("Comment not found"));

        commentEntity.setMessage(message);
        commentEntity.setCreatedAt(LocalDateTime.now());
        CommentEntity savedCommentEntity = commentRepository.save(commentEntity);
        return CommentMapper.toDTO(savedCommentEntity);
    }
}
