package com.example.sports_participant_be_main.controllers;

import com.example.sports_participant_be_main.dto.LocationDto;
import com.example.sports_participant_be_main.services.LocationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/staff/{staff_id}/gym_brands/{gym_brand_id}/locations")
public class LocationController {

    private final LocationService locationService;

    @PostMapping
    public ResponseEntity<LocationDto> add(
            @PathVariable("staff_id") UUID staffId,
            @PathVariable("gym_brand_id") UUID gymBrandId,
            @Valid @RequestBody LocationDto locationDto
    ) {
        return new ResponseEntity<>(
                locationService.save(locationDto.ofEntity(), gymBrandId).ofDto(),
                HttpStatus.CREATED
        );
    }
}
