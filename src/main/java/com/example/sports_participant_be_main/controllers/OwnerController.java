package com.example.sports_participant_be_main.controllers;

import com.example.sports_participant_be_main.dto.OwnerDto;
import com.example.sports_participant_be_main.services.OwnerService;
import com.example.sports_participant_be_main.utils.exceptions.owner.OwnerNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/owners")
@AllArgsConstructor
@Slf4j
public class OwnerController {

    private final OwnerService ownerService;

    @GetMapping("/{owner_id}")
    public ResponseEntity<OwnerDto> getById(@PathVariable("owner_id") UUID ownerId) {
        return new ResponseEntity<>(
                ownerService.findById(ownerId)
                        .orElseThrow(OwnerNotFoundException::new)
                        .ofDto(),
                HttpStatus.OK
        );
    }
}
