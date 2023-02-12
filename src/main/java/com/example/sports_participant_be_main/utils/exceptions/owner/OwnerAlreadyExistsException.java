package com.example.sports_participant_be_main.utils.exceptions.owner;

import com.example.sports_participant_be_main.utils.ResponseMessages;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
@Slf4j
public class OwnerAlreadyExistsException extends RuntimeException {
    private final String message = ResponseMessages.Owner.OWNER_EXISTS.message;

    public OwnerAlreadyExistsException() {
        super(ResponseMessages.Owner.OWNER_EXISTS.message);
        log.error(this.message, this);
    }
}
