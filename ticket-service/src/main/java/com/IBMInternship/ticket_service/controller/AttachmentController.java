package com.IBMInternship.ticket_service.controller;

import com.IBMInternship.ticket_service.mappers.AttachmentMapper;
import com.IBMInternship.ticket_service.model.dtos.AttachmentDTO;
import com.IBMInternship.ticket_service.model.entities.AttachmentEntity;
import com.IBMInternship.ticket_service.service.AttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/attachment")
public class AttachmentController {


    @Autowired
    private AttachmentService attachmentService;

    private static final Logger logger = LoggerFactory.getLogger(AttachmentController.class);



    @PostMapping(value = "/uploadFile/{ticketId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public AttachmentDTO uploadFile(@RequestPart("file") MultipartFile file, @PathVariable("ticketId") Long ticketId) {
        AttachmentEntity attachment = attachmentService.storeAttachment(file, ticketId);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/attachment/downloadAttachment/")
                .path(String.valueOf(attachment.getAttachmentId()))
                .toUriString();

        AttachmentDTO responseAttachment = AttachmentMapper.toDTO(attachment, fileDownloadUri, file.getSize());
        return responseAttachment;
    }


    @PostMapping(value = "/uploadMultipleFiles/{ticketId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public List<AttachmentDTO> uploadMultipleFiles(@RequestPart("files") MultipartFile[] files, @PathVariable("ticketId") Long ticketId) {
        List<AttachmentDTO> responses = new ArrayList<>();

        for (MultipartFile file : files) {
            try {
                logger.info("Uploading file: {}", file.getOriginalFilename());


               AttachmentDTO response = uploadFile(file, ticketId);
                responses.add(response);

                logger.info("Successfully uploaded file: {}", file.getOriginalFilename());

            } catch (Exception e) {
                // Log the error
                logger.error("Error uploading file: {}", file.getOriginalFilename(), e);
            }
        }

        return responses;
    }



    @GetMapping("/downloadAttachment/{attachmentId}")
    public ResponseEntity<Resource> downloadAttachment(@PathVariable Long attachmentId, HttpServletRequest request) {
        AttachmentEntity attachment = attachmentService.getAttachment(attachmentId);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(attachment.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + attachment.getFileName() + "\"")
                .body(new ByteArrayResource(attachment.getData()));
    }


}
