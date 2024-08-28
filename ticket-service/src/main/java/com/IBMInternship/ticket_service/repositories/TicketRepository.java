package com.IBMInternship.ticket_service.repositories;
import com.IBMInternship.ticket_service.model.entities.TicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends JpaRepository<TicketEntity, Long> {

}
