package com.septgroup.appointmentservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZonedDateTime;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = {NotFoundException.class})
    public ResponseEntity<Object> handleNotFoundException(NotFoundException e) {
        return handleGenericClientException(e);
    }

    @ExceptionHandler(value = {AlreadyExistException.class})
    public ResponseEntity<Object> handleAlreadyExistException(AlreadyExistException e) {
        return handleGenericClientException(e);
    }

    @ExceptionHandler(value = {InvalidIdException.class})
    public ResponseEntity<Object> handleInvalidIdException(InvalidIdException e) {
        return handleGenericClientException(e);
    }

    @ExceptionHandler(value = {InvalidTimeslotException.class})
    public ResponseEntity<Object> handleInvalidTimeslotException(InvalidTimeslotException e) {
        return handleGenericClientException(e);
    }

    private ResponseEntity<Object> handleGenericClientException(Exception e) {
        ApiExceptionDTO apiExceptionDTO = new ApiExceptionDTO(
                e.getMessage(),
                HttpStatus.BAD_REQUEST,
                ZonedDateTime.now()
        );
        return ResponseEntity.badRequest().body(apiExceptionDTO);
    }
}
