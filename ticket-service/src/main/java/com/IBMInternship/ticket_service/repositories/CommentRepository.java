package com.IBMInternship.ticket_service.repositories;
import com.IBMInternship.ticket_service.model.entities.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
List<CommentEntity> findByTicketId(Long ticketEntity);
}
