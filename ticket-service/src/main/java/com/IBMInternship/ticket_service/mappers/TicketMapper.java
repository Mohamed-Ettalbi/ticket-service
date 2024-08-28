package com.IBMInternship.ticket_service.mappers;

import com.IBMInternship.ticket_service.model.dtos.CreateTicketDTO;
import com.IBMInternship.ticket_service.model.dtos.TicketDTO;
import com.IBMInternship.ticket_service.model.dtos.UpdateTicketDTO;
import com.IBMInternship.ticket_service.model.entities.TicketEntity;

import java.util.List;
import java.util.stream.Collectors;


public class TicketMapper {

    // Map CreateTicketDTO to Ticket entity

   public static TicketEntity toEntityForCreate(CreateTicketDTO dto) {
       if (dto == null) {
           return null;
       }

       TicketEntity ticketEntity = new TicketEntity();
       ticketEntity.setTitle(dto.getTitle());
       ticketEntity.setDescription(dto.getDescription());
       ticketEntity.setPriority(dto.getPriority());

       return ticketEntity;
   }


   public static TicketEntity toEntityForUpdate(UpdateTicketDTO dto) {
       if (dto == null) {

           return null;
       }
       TicketEntity ticketEntity = new TicketEntity();
       ticketEntity.setTitle(dto.getTitle());
       ticketEntity.setDescription(dto.getDescription());
       ticketEntity.setPriority(dto.getPriority());
       return ticketEntity;
   }



    // Map Ticket entity to TicketDTO
   public static TicketDTO toDTO(TicketEntity ticketEntity){
       if (ticketEntity == null) {       return  null;}
       else{

           TicketDTO ticketDTO = new TicketDTO();
           ticketDTO.setTicketId(ticketEntity.getId());
           ticketDTO.setTitle(ticketEntity.getTitle());
           ticketDTO.setDescription(ticketEntity.getDescription());
           ticketDTO.setPriority(ticketEntity.getPriority());
           ticketDTO.setStatus(ticketEntity.getStatus());
           ticketDTO.setAssignedTo(ticketEntity.getAssignedTo());
           ticketDTO.setAssignedGroup(ticketEntity.getAssignedGroup());
           ticketDTO.setCreatedAt(ticketEntity.getCreatedAt());
           ticketDTO.setUpdatedAt(ticketEntity.getUpdatedAt());
           ticketDTO.setResolvedAt(ticketEntity.getResolvedAt());
           ticketDTO.setTicketCategoryName(ticketEntity.getTicketCategoryEntity().getName());
           return ticketDTO;
       }

   }

    public static List<TicketDTO> toDTOs(List<TicketEntity> ticketEntities) {
        return ticketEntities.stream()
                .map(TicketMapper::toDTO)
                .collect(Collectors.toList());
    }
}
