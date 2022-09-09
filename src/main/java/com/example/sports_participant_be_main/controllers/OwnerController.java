package com.example.sports_participant_be_main.controllers;

import com.example.sports_participant_be_main.models.Owner;
import com.example.sports_participant_be_main.repositories.OwnerRepo;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/owner")
@AllArgsConstructor
public class OwnerController {

    private final OwnerRepo ownerRepo;

    @PostMapping("/add")
    public void add(@RequestBody Owner owner){
        ownerRepo.save(owner);
    }
}
