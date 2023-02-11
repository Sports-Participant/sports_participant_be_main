package com.example.sports_participant_be_main.utils.exceptions;

import com.example.sports_participant_be_main.utils.ResponseMessages;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
@Slf4j
public class InternalException extends RuntimeException {
    private final String message = ResponseMessages.Other.INTERNAL_SERVER_ERROR.message;

    public InternalException() {
        super(ResponseMessages.Other.INTERNAL_SERVER_ERROR.message);
        log.error(this.message, this);
    }
}
