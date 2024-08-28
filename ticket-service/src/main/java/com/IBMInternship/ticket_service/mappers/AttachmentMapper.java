package com.IBMInternship.ticket_service.mappers;

import com.IBMInternship.ticket_service.model.dtos.AttachmentDTO;
import com.IBMInternship.ticket_service.model.entities.AttachmentEntity;

public class AttachmentMapper {

        public static AttachmentDTO toDTO(AttachmentEntity attachmentEntity , String fileDownloadUri ,Long size) {
            if (attachmentEntity == null) {
                return null;
            }

            AttachmentDTO attachmentDTO = new AttachmentDTO();
            attachmentDTO.setFileName(attachmentEntity.getFileName());
            //attachment.set(attachmentEntity.getTicket().getId());
            attachmentDTO.setFileType(attachmentEntity.getFileType());
            attachmentDTO.setFileDownloadUri(fileDownloadUri);
            attachmentDTO.setSize(size);


            return attachmentDTO;
        }
}
