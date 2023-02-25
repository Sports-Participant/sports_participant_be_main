package com.example.sports_participant_be_main.utils.exceptions.gym_brand;

import com.example.sports_participant_be_main.utils.ResponseMessages;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

@ResponseStatus(value = HttpStatus.CONFLICT)
@Slf4j
public class GymBrandIsAlreadyExistsException extends RuntimeException {
    private final String message = ResponseMessages.GymBrand.ALREADY_EXISTS.message;

    public GymBrandIsAlreadyExistsException() {
        super(ResponseMessages.GymBrand.ALREADY_EXISTS.message);
        log.error(message, this);
    }

    public GymBrandIsAlreadyExistsException(String name) {
        super(ResponseMessages.GymBrand.ALREADY_EXISTS.message + " name=" + name);
        log.error(message + " name=" + name, this);
    }

    public GymBrandIsAlreadyExistsException(UUID id) {
        super(ResponseMessages.GymBrand.ALREADY_EXISTS.message + " id=" + id);
        log.error(message + " id=" + id, this);
    }
}
