package com.IBMInternship.ticket_service.mappers;

import com.IBMInternship.ticket_service.model.dtos.CommentDTO;
import com.IBMInternship.ticket_service.model.entities.CommentEntity;

import java.util.List;
import java.util.stream.Collectors;


public class CommentMapper {
    public static CommentDTO toDTO(CommentEntity commentEntity) {
        if (commentEntity == null) {
            return null;
        }

        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setId(commentEntity.getCommentId());
        commentDTO.setTicketId(commentEntity.getTicket().getId());
        commentDTO.setMessage(commentEntity.getMessage());
        commentDTO.setCreatedAt(commentEntity.getCreatedAt());

        return commentDTO;
    }

    // Map list of Comment entities to list of CommentDTOs
    public static List<CommentDTO> toDTOs(List<CommentEntity> commentEntities) {
        return commentEntities.stream()
                .map(CommentMapper::toDTO)
                .collect(Collectors.toList());
    }
}