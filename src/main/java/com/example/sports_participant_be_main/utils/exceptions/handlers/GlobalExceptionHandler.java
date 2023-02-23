package com.example.sports_participant_be_main.utils.exceptions.handlers;

import com.example.sports_participant_be_main.utils.exceptions.ExceptionResponse;
import com.example.sports_participant_be_main.utils.exceptions.gym_brand.GymBrandIsAlreadyExistsException;
import com.example.sports_participant_be_main.utils.exceptions.other.InternalException;
import com.example.sports_participant_be_main.utils.exceptions.owner.OwnerAlreadyExistsException;
import com.example.sports_participant_be_main.utils.exceptions.owner.OwnerNotFoundException;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.security.auth.message.AuthException;
import java.time.ZonedDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InternalException.class)
    public ResponseEntity<ExceptionResponse> handleInternalException(Exception exception) {
        return new ResponseEntity<>(
                new ExceptionResponse(exception.getMessage(), ZonedDateTime.now()),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    @ExceptionHandler(OwnerNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleOwnerNotFoundException(Exception exception) {
        return new ResponseEntity<>(
                new ExceptionResponse(exception.getMessage(), ZonedDateTime.now()),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(AuthException.class)
    public ResponseEntity<ExceptionResponse> handleAuthException(Exception exception) {
        return new ResponseEntity<>(
                new ExceptionResponse(exception.getMessage(), ZonedDateTime.now(), exception.getCause()),
                HttpStatus.FORBIDDEN
        );
    }

    @ExceptionHandler(OwnerAlreadyExistsException.class)
    public ResponseEntity<ExceptionResponse> handleOwnerAlreadyExistsException(Exception exception) {
        return new ResponseEntity<>(
                new ExceptionResponse(exception.getMessage(), ZonedDateTime.now()),
                HttpStatus.CONFLICT
        );
    }

    @ExceptionHandler(GymBrandIsAlreadyExistsException.class)
    public ResponseEntity<ExceptionResponse> handleGymBrandHasAlreadyExistsException(Exception exception) {
        return new ResponseEntity<>(
                new ExceptionResponse(exception.getMessage(), ZonedDateTime.now()),
                HttpStatus.CONFLICT
        );
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<ExceptionResponse> handleExpiredJwtException(Exception exception) {
        return new ResponseEntity<>(
                new ExceptionResponse(exception.getMessage(), ZonedDateTime.now()),
                HttpStatus.UNAUTHORIZED
        );
    }
}
