package com.example.sports_participant_be_main.utils.exceptions.handlers.gym_brand;

import com.example.sports_participant_be_main.utils.exceptions.ExceptionResponse;
import com.example.sports_participant_be_main.utils.exceptions.gym_brand.GymBrandIsAlreadyExistsException;
import com.example.sports_participant_be_main.utils.exceptions.gym_brand.GymBrandNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZonedDateTime;

@ControllerAdvice
public class GymBrandExceptionHandler {

    @ExceptionHandler(GymBrandIsAlreadyExistsException.class)
    public ResponseEntity<ExceptionResponse> handleGymBrandHasAlreadyExistsException(Exception exception) {
        return new ResponseEntity<>(
                new ExceptionResponse(exception.getMessage(), ZonedDateTime.now()),
                HttpStatus.CONFLICT
        );
    }

    @ExceptionHandler(GymBrandNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleGymBrandNotFoundException(Exception exception) {
        return new ResponseEntity<>(
                new ExceptionResponse(exception.getMessage(), ZonedDateTime.now()),
                HttpStatus.NOT_FOUND
        );
    }
}