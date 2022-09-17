package com.example.sports_participant_be_main.controllers;

import com.example.sports_participant_be_main.models.dto.GymBrandDto;
import com.example.sports_participant_be_main.services.GymBrandService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/gym_brand")
public class GymBrandController {

    private final GymBrandService gymBrandService;

    @PostMapping("/add")
    private GymBrandDto add(@Valid @RequestBody GymBrandDto gymBrandDto) {
        return gymBrandService.save(gymBrandDto).ofDto();
    }
}
