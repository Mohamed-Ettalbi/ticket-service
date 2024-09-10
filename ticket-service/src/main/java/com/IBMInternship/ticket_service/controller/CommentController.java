package com.IBMInternship.ticket_service.controller;
import com.IBMInternship.ticket_service.model.dtos.CommentDTO;
import com.IBMInternship.ticket_service.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@RestController
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    private  CommentService commentService;



    @PostMapping("/{ticketId}")
    public ResponseEntity<CommentDTO> addComment(@PathVariable Long ticketId, @RequestBody CommentDTO commentDTO) {
        CommentDTO savedComment = commentService.addCommentToTicket(ticketId, commentDTO);
        return ResponseEntity.ok(savedComment);
    }

    @GetMapping("/{ticketId}")
    public ResponseEntity<List<CommentDTO>> getCommentsByTicket(@PathVariable Long ticketId) {
        List<CommentDTO> comments = commentService.getCommentsByTicketId(ticketId);
        return ResponseEntity.ok(comments);
    }

    @DeleteMapping("/delete/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable Long commentId ,@RequestParam(value = "email", required = false) String email) {

        commentService.deleteCommentById(commentId , email);
        return ResponseEntity.ok("Comment deleted successfully");
    }
    @PutMapping("/update/{commentId}")
    public ResponseEntity<CommentDTO> updateComment(@PathVariable Long commentId, @RequestBody String message) {
        CommentDTO updatedComment= commentService.updateComment(commentId,message);
        return ResponseEntity.ok(updatedComment);
    }
}