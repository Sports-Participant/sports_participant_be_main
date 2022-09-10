package com.example.sports_participant_be_main.utils.exceptions;

import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
@NoArgsConstructor
public class InternalException extends RuntimeException{

    public InternalException(String message) {
        super(message);
    }

}
