package com.septgroup.appointmentservice.exception;

public class InvalidTimeslotException extends RuntimeException {
    public InvalidTimeslotException(String message) {
        super(message);
    }

    public InvalidTimeslotException(String message, Throwable cause) {
        super(message, cause);
    }
}
