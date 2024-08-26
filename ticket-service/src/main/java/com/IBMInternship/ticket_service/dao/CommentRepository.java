package com.IBMInternship.ticket_service.dao;
import com.IBMInternship.ticket_service.model.Comment;
import com.IBMInternship.ticket_service.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
List<Comment> findByTicketId(Long ticket);
}
