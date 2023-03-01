package com.example.sports_participant_be_main.controllers;

import com.example.sports_participant_be_main.dto.LocationRoomDto;
import com.example.sports_participant_be_main.services.LocationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/gym_brands/{gym_brand_id}/locations/{location_id}/rooms")
@AllArgsConstructor
@Slf4j
public class LocationRoomController {

    private final LocationService locationService;

    @PostMapping
    public ResponseEntity<LocationRoomDto> add(
            @PathVariable("gym_brand_id") UUID gymBrandId,
            @PathVariable("location_id") UUID locationId,
            @Valid @RequestBody LocationRoomDto locationRoomDto
    ) {
        return new ResponseEntity<>(
                this.locationService.saveLocationRoom(locationRoomDto.ofEntity(), locationRoomDto.getLocation_id()).ofDto(),
                HttpStatus.CREATED
        );
    }
}
