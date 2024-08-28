package com.IBMInternship.ticket_service.service;

import com.IBMInternship.ticket_service.model.dtos.AttachmentDTO;
import com.IBMInternship.ticket_service.model.entities.AttachmentEntity;
import com.IBMInternship.ticket_service.model.entities.TicketEntity;
import com.IBMInternship.ticket_service.repositories.AttachmentRepository;
import com.IBMInternship.ticket_service.repositories.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.logging.Logger;

@Service
public class AttachmentService {
    @Autowired
    private AttachmentRepository attachmentRepository;
    @Autowired
    private TicketRepository ticketRepository;

    public AttachmentEntity storeAttachment(MultipartFile file, Long id) {
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // Check if the file's name contains invalid characters
            if (fileName.contains("..")) {
                throw new RuntimeException("Sorry! Filename contains invalid path sequence ");
            }

            AttachmentEntity attachment = new AttachmentEntity();
            attachment.setFileName(fileName);
            attachment.setFileType(file.getContentType());
            attachment.setData(file.getBytes());
            attachment.setCreatedAt(LocalDateTime.now());

            TicketEntity ticketEntity = ticketRepository.findById(id)
                    .orElseThrow(()->
                            new RuntimeException("no ticket by the id : " +id));//to add exceptions later on
            attachment.setTicket(ticketEntity);

            return attachmentRepository.save(attachment);

        } catch (IOException ex) {
            throw new RuntimeException("Could not store this file . Please try again!");
        }
    }

    public AttachmentEntity getAttachment(Long attachmentId) {
        return attachmentRepository.findById(attachmentId)
                .orElseThrow(() -> new RuntimeException("File not found " ));
    }
}

