package com.example.sports_participant_be_main.utils.exceptions.location;

import com.example.sports_participant_be_main.utils.ResponseMessages;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Collection;
import java.util.UUID;

@ResponseStatus(value = HttpStatus.CONFLICT)
@Slf4j
public class LocationIsAlreadyExistsException extends RuntimeException {
    private final String message = ResponseMessages.Location.ALREADY_EXISTS.message;

    public LocationIsAlreadyExistsException() {
        super(ResponseMessages.Location.ALREADY_EXISTS.message);
        log.error(message, this);
    }

    public LocationIsAlreadyExistsException(String street, Integer streetNumber) {
        super(ResponseMessages.Location.ALREADY_EXISTS.message + " street=" + street + " streetNumber=" + streetNumber);
        log.error(message + " street=" + street + " streetNumber=" + streetNumber, this);
    }
}
