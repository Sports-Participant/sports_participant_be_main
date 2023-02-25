package com.example.sports_participant_be_main.utils.exceptions.handlers.location.location_room;

import com.example.sports_participant_be_main.utils.exceptions.ExceptionResponse;
import com.example.sports_participant_be_main.utils.exceptions.location.location_room.LocationRoomNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZonedDateTime;

@ControllerAdvice
public class LocationRoomExceptionHandler {

    @ExceptionHandler(LocationRoomNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleLocationRoomNotFoundException(Exception exception) {
        return new ResponseEntity<>(
                new ExceptionResponse(exception.getMessage(), ZonedDateTime.now()),
                HttpStatus.NOT_FOUND
        );
    }
}