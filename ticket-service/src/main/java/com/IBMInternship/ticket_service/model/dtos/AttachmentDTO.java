package com.IBMInternship.ticket_service.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttachmentDTO {
    private String fileName;
    private String fileDownloadUri;
    private String fileType;
    private long size;
}
