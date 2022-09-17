package com.example.sports_participant_be_main.utils.exceptions;

import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
@NoArgsConstructor
public class GymBrandHasAlreadyExistsException extends RuntimeException {

    public GymBrandHasAlreadyExistsException(String message) {
        super(message);
    }
}
