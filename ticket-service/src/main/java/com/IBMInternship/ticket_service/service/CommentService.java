package com.IBMInternship.ticket_service.service;

import com.IBMInternship.ticket_service.dao.CommentRepository;
import com.IBMInternship.ticket_service.dao.TicketRepository;
import com.IBMInternship.ticket_service.dao.dto.CommentDTO;
import com.IBMInternship.ticket_service.dao.mappers.CommentMapper;
import com.IBMInternship.ticket_service.model.Comment;
import com.IBMInternship.ticket_service.model.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final TicketRepository ticketRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository, TicketRepository ticketRepository) {
        this.commentRepository = commentRepository;
        this.ticketRepository = ticketRepository;
    }

    public CommentDTO addCommentToTicket(Long ticketId, String message) {
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));

        Comment comment = new Comment();
        comment.setTicket(ticket);
        comment.setMessage(message);
        comment.setCreatedAt(LocalDateTime.now());
        //    Comment comment = new Comment();
//    comment.setTicket(ticket);
//    comment.setMessage(message);
//    comment.setCreatedAt(LocalDateTime.now());
//    Comment savedComment = commentRepository.save(comment);


        Comment savedComment = commentRepository.save(comment);
        return CommentMapper.toDTO(savedComment);
    }

    public List<CommentDTO> getCommentsByTicketId(Long ticketId) {
        List<Comment> comments = commentRepository.findByTicketId(ticketId);
        return CommentMapper.toDTOs(comments);
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

