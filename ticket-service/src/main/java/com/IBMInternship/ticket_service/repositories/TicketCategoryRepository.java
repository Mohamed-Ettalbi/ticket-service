package com.IBMInternship.ticket_service.repositories;
import com.IBMInternship.ticket_service.model.entities.TicketCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketCategoryRepository extends JpaRepository<TicketCategoryEntity, Long> {
}
