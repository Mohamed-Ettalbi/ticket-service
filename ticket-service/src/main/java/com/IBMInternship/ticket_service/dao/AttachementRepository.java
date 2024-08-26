package com.IBMInternship.ticket_service.dao;
import com.IBMInternship.ticket_service.model.Attachment;
import com.IBMInternship.ticket_service.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttachementRepository extends JpaRepository<Attachment, Long> {
}
