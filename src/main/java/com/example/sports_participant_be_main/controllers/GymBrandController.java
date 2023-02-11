package com.example.sports_participant_be_main.controllers;

import com.example.sports_participant_be_main.models.GymBrand;
import com.example.sports_participant_be_main.dto.GymBrandDto;
import com.example.sports_participant_be_main.services.GymBrandService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("/owners/{owner_id}/gym_brands")
public class GymBrandController {

    private final GymBrandService gymBrandService;

    @PostMapping("/add")
    public ResponseEntity<GymBrandDto> add(@PathVariable("owner_id") UUID ownerId, @Valid @RequestBody GymBrandDto gymBrandDto) {
        return new ResponseEntity<>(
                gymBrandService.save(gymBrandDto.ofEntity(), ownerId).ofDto(),
                HttpStatus.CREATED
        );
    }

    @GetMapping
    public ResponseEntity<Collection<GymBrandDto>> getAllByOwnerId(@PathVariable("owner_id") UUID ownerId) {
        return new ResponseEntity<>(this.gymBrandService.getAllByOwnerId(ownerId)
                .stream()
                .map(GymBrand::ofDto)
                .collect(Collectors.toList()),
                HttpStatus.OK
        );
    }
}
