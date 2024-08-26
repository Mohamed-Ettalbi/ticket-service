package com.IBMInternship.ticket_service.dao;
import com.IBMInternship.ticket_service.model.Ticket;
import com.IBMInternship.ticket_service.model.TicketCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketCategoryRepository extends JpaRepository<TicketCategory, Long> {
}
