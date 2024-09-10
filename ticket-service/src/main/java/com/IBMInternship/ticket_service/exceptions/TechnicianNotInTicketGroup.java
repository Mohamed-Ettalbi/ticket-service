package com.IBMInternship.ticket_service.exceptions;

public class TechnicianNotInTicketGroup extends RuntimeException {
    public TechnicianNotInTicketGroup(String message) {
        super(message);
    }
}
