package com.example.sports_participant_be_main.utils.exceptions.handlers.owner;

import com.example.sports_participant_be_main.utils.exceptions.ExceptionResponse;
import com.example.sports_participant_be_main.utils.exceptions.owner.OwnerAlreadyExistsException;
import com.example.sports_participant_be_main.utils.exceptions.owner.OwnerNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZonedDateTime;

@ControllerAdvice
public class OwnerExceptionHandler {

    @ExceptionHandler(OwnerAlreadyExistsException.class)
    public ResponseEntity<ExceptionResponse> handleOwnerAlreadyExistsException(Exception exception) {
        return new ResponseEntity<>(
                new ExceptionResponse(exception.getMessage(), ZonedDateTime.now()),
                HttpStatus.CONFLICT
        );
    }

    @ExceptionHandler(OwnerNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleOwnerNotFoundException(Exception exception) {
        return new ResponseEntity<>(
                new ExceptionResponse(exception.getMessage(), ZonedDateTime.now()),
                HttpStatus.NOT_FOUND
        );
    }
}