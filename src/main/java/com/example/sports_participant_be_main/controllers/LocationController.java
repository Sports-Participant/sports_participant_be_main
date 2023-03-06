package com.example.sports_participant_be_main.controllers;

import com.example.sports_participant_be_main.dto.LocationDto;
import com.example.sports_participant_be_main.models.Location;
import com.example.sports_participant_be_main.services.LocationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/gym_brands/{gym_brand_id}/locations")
public class LocationController {

    private final LocationService locationService;

    @PostMapping
    public ResponseEntity<LocationDto> add(
            @PathVariable("gym_brand_id") UUID gymBrandId,
            @Valid @RequestBody LocationDto locationDto
    ) {
        return new ResponseEntity<>(
                this.locationService.saveLocation(locationDto.ofEntity(), gymBrandId, locationDto.getRoom_ids()).ofDto(),
                HttpStatus.CREATED
        );
    }

    @GetMapping
    public ResponseEntity<Collection<LocationDto>> getAllByGymBrandId(
            @PathVariable("gym_brand_id") UUID gymBrandId
    ) {
        return new ResponseEntity<>(
                this.locationService.getAllLocationByGymBrandId(gymBrandId)
                        .stream()
                        .map(Location::ofDto)
                        .collect(Collectors.toSet()),
                HttpStatus.OK
        );
    }
}
