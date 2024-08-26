package com.IBMInternship.ticket_service.dao.mappers;

import com.IBMInternship.ticket_service.dao.dto.CommentDTO;
import com.IBMInternship.ticket_service.model.Comment;

import java.util.List;
import java.util.stream.Collectors;


public class CommentMapper {
    public static CommentDTO toDTO(Comment comment) {
        if (comment == null) {
            return null;
        }

        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setId(comment.getCommentId());
        commentDTO.setTicketId(comment.getTicket().getId());
        commentDTO.setMessage(comment.getMessage());
        commentDTO.setCreatedAt(comment.getCreatedAt());

        return commentDTO;
    }

    // Map list of Comment entities to list of CommentDTOs
    public static List<CommentDTO> toDTOs(List<Comment> comments) {
        return comments.stream()
                .map(CommentMapper::toDTO)
                .collect(Collectors.toList());
    }
}
