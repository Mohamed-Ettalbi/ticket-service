package com.IBMInternship.ticket_service.controller;

import com.IBMInternship.ticket_service.dao.dto.CommentDTO;
import com.IBMInternship.ticket_service.model.Comment;
import com.IBMInternship.ticket_service.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/comments")
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/{ticketId}")
    public ResponseEntity<CommentDTO> addComment(@PathVariable Long ticketId, @RequestBody String message) {
        CommentDTO savedComment = commentService.addCommentToTicket(ticketId, message);
        return ResponseEntity.ok(savedComment);
    }

    @GetMapping("/{ticketId}")
    public ResponseEntity<List<CommentDTO>> getCommentsByTicket(@PathVariable Long ticketId) {
        List<CommentDTO> comments = commentService.getCommentsByTicketId(ticketId);
        return ResponseEntity.ok(comments);
    }
}

