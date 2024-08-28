package com.IBMInternship.ticket_service.service;

import com.IBMInternship.ticket_service.model.dtos.CommentDTO;
import com.IBMInternship.ticket_service.repositories.CommentRepository;
import com.IBMInternship.ticket_service.repositories.TicketRepository;
import com.IBMInternship.ticket_service.mappers.CommentMapper;
import com.IBMInternship.ticket_service.model.entities.CommentEntity;
import com.IBMInternship.ticket_service.model.entities.TicketEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class CommentService {

    @Autowired
    private  CommentRepository commentRepository;
    @Autowired
    private  TicketRepository ticketRepository;



    public CommentDTO addCommentToTicket(Long ticketId, String message) {
        TicketEntity ticketEntity = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));

        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setTicket(ticketEntity);
        commentEntity.setMessage(message);
        commentEntity.setCreatedAt(LocalDateTime.now());
        //    Comment comment = new Comment();
//    comment.setTicket(ticket);
//    comment.setMessage(message);
//    comment.setCreatedAt(LocalDateTime.now());
//    Comment savedComment = commentRepository.save(comment);


        CommentEntity savedCommentEntity = commentRepository.save(commentEntity);
        return CommentMapper.toDTO(savedCommentEntity);
    }

    public List<CommentDTO> getCommentsByTicketId(Long ticketId) {
        List<CommentEntity> commentEntities = commentRepository.findByTicketId(ticketId);
        return CommentMapper.toDTOs(commentEntities);
    }

    public void deleteCommentById(Long ticketId) {
   if (commentRepository.findById(ticketId).isPresent()) {
       commentRepository.deleteById(ticketId);
   }else{
       throw new RuntimeException("Comment not found");
   }
    }

    public CommentDTO updateComment(Long id, String message ) {
        if (commentRepository.findById(id).isPresent()) {
          CommentEntity updatedCommentEntity =   commentRepository.findById(id).get();
          updatedCommentEntity.setMessage(message);
          CommentEntity savedCommentEntity = commentRepository.save(updatedCommentEntity);
          return CommentMapper.toDTO(savedCommentEntity);

        }else{
            throw new RuntimeException("Comment not found");
        }



    }
}
//public CommentDTO addCommentToTicket(Long ticketId, String message) {
//    Ticket ticket = ticketRepository.findById(ticketId)
//            .orElseThrow(() -> new RuntimeException("Ticket not found"));
//
//    Comment comment = new Comment();
//    comment.setTicket(ticket);
//    comment.setMessage(message);
//    comment.setCreatedAt(LocalDateTime.now());
//    Comment savedComment = commentRepository.save(comment);
//
//    CommentDTO commentDTO = new CommentDTO();
//    commentDTO.setId(savedComment.getCommentId());
//    commentDTO.setMessage(message);
//    commentDTO.setTicketId(ticketId);
//    commentDTO.setCreatedAt(savedComment.getCreatedAt());
//
//
//
//
//    return commentDTO;
//}
//
//public List<Comment> getCommentsByTicketId(Long ticketId) {
//    List<Comment> comments = commentRepository.findByTicketId(ticketId);
//    return comments;
//}
//}

