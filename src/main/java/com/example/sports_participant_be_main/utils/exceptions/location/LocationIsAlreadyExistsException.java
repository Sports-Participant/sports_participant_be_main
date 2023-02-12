package com.example.sports_participant_be_main.utils.exceptions.location;

import com.example.sports_participant_be_main.utils.ResponseMessages;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
@Slf4j
public class LocationIsAlreadyExistsException extends RuntimeException {
    private final String message = ResponseMessages.Location.LOCATION_EXISTS.message;

    public LocationIsAlreadyExistsException() {
        super(ResponseMessages.Location.LOCATION_EXISTS.message);
        log.error(message, this);
    }
}
