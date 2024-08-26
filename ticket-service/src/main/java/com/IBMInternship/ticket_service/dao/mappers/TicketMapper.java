package com.IBMInternship.ticket_service.dao.mappers;

import com.IBMInternship.ticket_service.dao.dto.CreateTicketDTO;
import com.IBMInternship.ticket_service.dao.dto.TicketDTO;
import com.IBMInternship.ticket_service.dao.dto.UpdateTicketDTO;
import com.IBMInternship.ticket_service.model.Ticket;

import java.util.List;
import java.util.stream.Collectors;


public class TicketMapper {

    // Map CreateTicketDTO to Ticket entity

   public static Ticket toEntityForCreate(CreateTicketDTO dto) {
       if (dto == null) {
           return null;
       }

       Ticket ticket = new Ticket();
       ticket.setTitle(dto.getTitle());
       ticket.setDescription(dto.getDescription());
       ticket.setPriority(dto.getPriority());

       return ticket;
   }


   public static Ticket toEntityForUpdate(UpdateTicketDTO dto) {
       if (dto == null) {

           return null;
       }
       Ticket ticket = new Ticket();
       ticket.setTitle(dto.getTitle());
       ticket.setDescription(dto.getDescription());
       ticket.setPriority(dto.getPriority());
       return ticket;
   }



    // Map Ticket entity to TicketDTO
   public static TicketDTO toDTO(Ticket ticket){
       if (ticket == null) {       return  null;}
       else{

           TicketDTO ticketDTO = new TicketDTO();
           ticketDTO.setTicketId(ticket.getId());
           ticketDTO.setTitle(ticket.getTitle());
           ticketDTO.setDescription(ticket.getDescription());
           ticketDTO.setPriority(ticket.getPriority());
           ticketDTO.setStatus(ticket.getStatus());
           ticketDTO.setAssignedTo(ticket.getAssignedTo());
           ticketDTO.setAssignedGroup(ticket.getAssignedGroup());
           ticketDTO.setCreatedAt(ticket.getCreatedAt());
           ticketDTO.setUpdatedAt(ticket.getUpdatedAt());
           ticketDTO.setResolvedAt(ticket.getResolvedAt());
           ticketDTO.setTicketCategoryName(ticket.getTicketCategory().getName());
           return ticketDTO;
       }

   }

    public static List<TicketDTO> toDTOs(List<Ticket> tickets) {
        return tickets.stream()
                .map(TicketMapper::toDTO)
                .collect(Collectors.toList());
    }
}
