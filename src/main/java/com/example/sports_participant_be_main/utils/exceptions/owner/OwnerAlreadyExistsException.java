package com.example.sports_participant_be_main.utils.exceptions.owner;

import com.example.sports_participant_be_main.utils.ResponseMessages;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

@ResponseStatus(value = HttpStatus.CONFLICT)
@Slf4j
public class OwnerAlreadyExistsException extends RuntimeException {
    private final String message = ResponseMessages.Owner.ALREADY_EXISTS.message;

    public OwnerAlreadyExistsException() {
        super(ResponseMessages.Owner.ALREADY_EXISTS.message);
        log.error(this.message, this);
    }

    public OwnerAlreadyExistsException(UUID id) {
        super(ResponseMessages.Owner.ALREADY_EXISTS.message + " id=" + id);
        log.error(message + " id=" + id, this);
    }

    public OwnerAlreadyExistsException(String email) {
        super(ResponseMessages.Owner.ALREADY_EXISTS.message + " email=" + email);
        log.error(message + " email=" + email, this);
    }
}
