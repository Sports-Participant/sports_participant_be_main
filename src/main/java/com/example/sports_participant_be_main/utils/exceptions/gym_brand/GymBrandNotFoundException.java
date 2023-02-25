package com.example.sports_participant_be_main.utils.exceptions.gym_brand;

import com.example.sports_participant_be_main.utils.ResponseMessages;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
@Slf4j
public class GymBrandNotFoundException extends RuntimeException {
    private final String message = ResponseMessages.GymBrand.NOT_FOUND.message;

    public GymBrandNotFoundException() {
        super(ResponseMessages.GymBrand.NOT_FOUND.message);
        log.error(message, this);
    }

    public GymBrandNotFoundException(UUID id) {
        super(ResponseMessages.GymBrand.NOT_FOUND.message + " id=" + id);
        log.error(message + " id=" + id, this);
    }
}
