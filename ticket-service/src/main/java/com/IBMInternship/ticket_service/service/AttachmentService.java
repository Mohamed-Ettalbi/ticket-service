package com.IBMInternship.ticket_service.service;

import com.IBMInternship.ticket_service.exceptions.AttachmentNotFoundException;
import com.IBMInternship.ticket_service.exceptions.FileStorageException;
import com.IBMInternship.ticket_service.exceptions.InvalidFileNameException;
import com.IBMInternship.ticket_service.exceptions.TicketNotFoundException;
import com.IBMInternship.ticket_service.model.dtos.AttachmentDTO;
import com.IBMInternship.ticket_service.model.entities.AttachmentEntity;
import com.IBMInternship.ticket_service.model.entities.TicketEntity;
import com.IBMInternship.ticket_service.repositories.AttachmentRepository;
import com.IBMInternship.ticket_service.repositories.TicketRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class AttachmentService {

    private static final Logger logger = LoggerFactory.getLogger(TicketService.class);

    @Autowired
    private AttachmentRepository attachmentRepository;

    @Autowired
    private TicketRepository ticketRepository;

    public AttachmentEntity storeAttachment(MultipartFile file, Long id) {
        // Normalize file name
        logger.debug("method storeAttachment has been caled");

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // Check if the file's name contains invalid characters
            if (fileName.contains("..")) {
                throw new InvalidFileNameException("Sorry! Filename contains invalid path sequence: " + fileName);
            }

            AttachmentEntity attachment = new AttachmentEntity();
            attachment.setFileName(fileName);
            attachment.setFileType(file.getContentType());
            attachment.setData(file.getBytes());
            attachment.setCreatedAt(LocalDateTime.now());
            attachment.setSize(file.getSize());

            TicketEntity ticketEntity = ticketRepository.findById(id)
                    .orElseThrow(() -> new TicketNotFoundException("No ticket found with the ID: " + id));

            attachment.setTicket(ticketEntity);

            return attachmentRepository.save(attachment);

        } catch (IOException ex) {
            throw new FileStorageException("Could not store the file " + fileName + ". Please try again!");
        }
    }

    public AttachmentEntity getAttachment(Long attachmentId) {
        return attachmentRepository.findById(attachmentId)
                .orElseThrow(() -> new AttachmentNotFoundException("File not found with ID: " + attachmentId));
    }

    public List<AttachmentEntity> getAttachmentsByTicketId(Long ticketId) {
        return attachmentRepository.findByTicketId(ticketId);
    }

    public void deleteAttachmentById(Long attachmentId){
          attachmentRepository.deleteById(attachmentId);
    }

}
