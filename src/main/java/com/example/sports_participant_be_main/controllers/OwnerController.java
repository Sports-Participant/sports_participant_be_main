package com.example.sports_participant_be_main.controllers;

import com.example.sports_participant_be_main.models.dto.OwnerDto;
import com.example.sports_participant_be_main.services.OwnerService;
import com.example.sports_participant_be_main.utils.exceptions.OwnerNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/owners")
@AllArgsConstructor
@Slf4j
public class OwnerController {

    private final OwnerService ownerService;

    @PostMapping("/add")
    public ResponseEntity<OwnerDto> add(@Valid @RequestBody OwnerDto ownerDto){
        return new ResponseEntity<>(
                ownerService.save(ownerDto.ofEntity()).ofDto(),
                HttpStatus.CREATED
        );
    }

    @GetMapping("/{owner_id}")
    public ResponseEntity<OwnerDto> getById(@PathVariable("owner_id") UUID ownerId) {
        throw new OwnerNotFoundException();
//        return new ResponseEntity<>(
//                ownerService.getById(ownerId)
//                        .orElseThrow(OwnerNotFoundException::new)
//                        .ofDto(),
//                HttpStatus.OK
//        );
    }
}
