package com.example.sports_participant_be_main.controllers;

import com.example.sports_participant_be_main.models.Owner;
import com.example.sports_participant_be_main.services.OwnerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/owner")
@AllArgsConstructor
public class OwnerController {

    private final OwnerService ownerService;

    @PostMapping("/add")
    public void add(@Valid @RequestBody Owner owner){
        ownerService.save(owner);
    }
}
