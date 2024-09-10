package com.IBMInternship.ticket_service.repositories;
import com.IBMInternship.ticket_service.model.entities.AttachmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttachmentRepository extends JpaRepository<AttachmentEntity, Long> {
    List<AttachmentEntity> findByTicketId(Long ticketId);

}
