package com.IBMInternship.ticket_service.exceptions;

public class AttachmentNotFoundException extends RuntimeException {
    public AttachmentNotFoundException(String message) {
        super(message);
    }
}
