package com.example.sports_participant_be_main.utils.exceptions.handlers.other;

import com.example.sports_participant_be_main.utils.exceptions.ExceptionResponse;
import com.example.sports_participant_be_main.utils.exceptions.other.InternalException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZonedDateTime;

@ControllerAdvice
public class OtherExceptionHandler {

    @ExceptionHandler(InternalException.class)
    public ResponseEntity<ExceptionResponse> handleInternalException(Exception exception) {
        return new ResponseEntity<>(
                new ExceptionResponse(exception.getMessage(), ZonedDateTime.now()),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }


}