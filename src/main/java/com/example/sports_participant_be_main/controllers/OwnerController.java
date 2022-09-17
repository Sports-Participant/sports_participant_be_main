package com.example.sports_participant_be_main.controllers;

import com.example.sports_participant_be_main.models.dto.OwnerDto;
import com.example.sports_participant_be_main.services.OwnerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/owner")
@AllArgsConstructor
@Slf4j
public class OwnerController {

    private final OwnerService ownerService;

    @PostMapping("/add")
    public ResponseEntity<OwnerDto> add(@Valid @RequestBody OwnerDto ownerDto){
        return new ResponseEntity<>(ownerService.save(ownerDto.ofEntity()).ofDto(), HttpStatus.CREATED);
    }
}
