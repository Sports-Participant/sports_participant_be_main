package com.example.sports_participant_be_main.utils.exceptions.location;

import com.example.sports_participant_be_main.utils.ResponseMessages;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Collection;
import java.util.UUID;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
@Slf4j
public class AnyLocationNotFoundException extends RuntimeException {
    private final String message = ResponseMessages.Location.ANY_LOCATION_NOT_FOUND.message;

    public AnyLocationNotFoundException() {
        super(ResponseMessages.Location.ANY_LOCATION_NOT_FOUND.message);
        log.error(message, this);
    }

    public AnyLocationNotFoundException(Collection<UUID> ids) {
        super(ResponseMessages.Location.ANY_LOCATION_NOT_FOUND.message + " ids={" + ids.stream().map(UUID::toString).reduce("", (res, element) -> res + ", " + element) + "}");
        log.error(message + " ids={" + ids.stream().map(UUID::toString).reduce("", (res, element) -> res + ", " + element) + "}", this);
    }
}
