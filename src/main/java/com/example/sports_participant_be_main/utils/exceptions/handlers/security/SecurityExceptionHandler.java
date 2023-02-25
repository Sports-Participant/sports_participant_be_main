package com.example.sports_participant_be_main.utils.exceptions.handlers.security;

import com.example.sports_participant_be_main.utils.exceptions.ExceptionResponse;
import com.example.sports_participant_be_main.utils.exceptions.security.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZonedDateTime;

@ControllerAdvice
public class SecurityExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleOwnerAlreadyExistsException(Exception exception) {
        return new ResponseEntity<>(
                new ExceptionResponse(exception.getMessage(), ZonedDateTime.now()),
                HttpStatus.NOT_FOUND
        );
    }
}