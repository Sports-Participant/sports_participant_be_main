package com.example.sports_participant_be_main.utils.exceptions;

import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
@NoArgsConstructor
public class OwnerNotFoundException extends RuntimeException {

    public OwnerNotFoundException(String message) {
        super(message);
    }
}
