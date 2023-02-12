package com.example.sports_participant_be_main.utils.exceptions.security;

import com.example.sports_participant_be_main.utils.ResponseMessages;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
@Slf4j
public class UserNotFoundException extends RuntimeException{
    private final String message = ResponseMessages.Security.USER_NOT_FOUND.message;

    public UserNotFoundException() {
        super(ResponseMessages.Security.USER_NOT_FOUND.message);
        log.error(this.message, this);
    }
}
