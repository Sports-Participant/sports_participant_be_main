package com.example.sports_participant_be_main.utils.exceptions.handlers.location;

import com.example.sports_participant_be_main.utils.exceptions.ExceptionResponse;
import com.example.sports_participant_be_main.utils.exceptions.location.AnyLocationNotFoundException;
import com.example.sports_participant_be_main.utils.exceptions.location.LocationIsAlreadyExistsException;
import com.example.sports_participant_be_main.utils.exceptions.location.LocationNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZonedDateTime;

@ControllerAdvice
public class LocationExceptionHandler {

    @ExceptionHandler(AnyLocationNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleAnyLocationNotFoundException(Exception exception) {
        return new ResponseEntity<>(
                new ExceptionResponse(exception.getMessage(), ZonedDateTime.now()),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(LocationIsAlreadyExistsException.class)
    public ResponseEntity<ExceptionResponse> handleLocationIsAlreadyExistsException(Exception exception) {
        return new ResponseEntity<>(
                new ExceptionResponse(exception.getMessage(), ZonedDateTime.now()),
                HttpStatus.CONFLICT
        );
    }

    @ExceptionHandler(LocationNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleLocationNotFoundException(Exception exception) {
        return new ResponseEntity<>(
                new ExceptionResponse(exception.getMessage(), ZonedDateTime.now()),
                HttpStatus.NOT_FOUND
        );
    }
}