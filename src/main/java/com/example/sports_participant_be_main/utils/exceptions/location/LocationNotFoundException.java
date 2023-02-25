package com.example.sports_participant_be_main.utils.exceptions.location;

import com.example.sports_participant_be_main.utils.ResponseMessages;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
@Slf4j
public class LocationNotFoundException extends RuntimeException {
    private final String message = ResponseMessages.Location.NOT_FOUND.message;

    public LocationNotFoundException() {
        super(ResponseMessages.Location.NOT_FOUND.message);
        log.error(message, this);
    }

    public LocationNotFoundException(UUID id) {
        super(ResponseMessages.Location.NOT_FOUND.message + " id=" + id);
        log.error(message + " id=" + id, this);
    }
}
