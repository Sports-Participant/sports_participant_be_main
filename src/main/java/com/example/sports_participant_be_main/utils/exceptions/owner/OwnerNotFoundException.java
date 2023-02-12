package com.example.sports_participant_be_main.utils.exceptions.owner;

import com.example.sports_participant_be_main.utils.ResponseMessages;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
@Slf4j
public class OwnerNotFoundException extends RuntimeException {
    private final String message = ResponseMessages.Owner.NOT_FOUND.message;

    public OwnerNotFoundException() {
        super(ResponseMessages.Owner.NOT_FOUND.message);
        log.error(message, this);
    }
}
