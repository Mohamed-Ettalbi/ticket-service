package com.IBMInternship.ticket_service.exceptions;

public class TicketCategoryNotFoundException extends RuntimeException {
    public TicketCategoryNotFoundException(String message) {
        super(message);
    }
}
