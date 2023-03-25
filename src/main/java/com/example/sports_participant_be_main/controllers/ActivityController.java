package com.example.sports_participant_be_main.controllers;

import com.example.sports_participant_be_main.dto.ActivityDto;
import com.example.sports_participant_be_main.dto.ActivityPriceDto;
import com.example.sports_participant_be_main.models.Activity;
import com.example.sports_participant_be_main.services.ActivityService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/gym_brands/{gym_brand_id}/locations/{location_id}/activities")
public class ActivityController {

    private final ActivityService activityService;

    @PostMapping
    public ResponseEntity<ActivityDto> add(
            @PathVariable("gym_brand_id") UUID gymBrandId,
            @PathVariable("location_id") UUID locationId,
            @Valid @RequestBody ActivityDto activityDto,
            @Valid @RequestBody ActivityPriceDto activityPriceDto
    ) {
        return new ResponseEntity<>(
                this.activityService.save(
                        activityDto.ofEntity(),
                        activityPriceDto.ofEntity(),
                        locationId,
                        activityDto.getEmployeeIds()
                ).ofDto(),
                HttpStatus.CREATED
        );
    }

    @PostMapping("/without_price")
    public ResponseEntity<ActivityDto> addWithoutPrice(
            @PathVariable("gym_brand_id") UUID gymBrandId,
            @PathVariable("location_id") UUID locationId,
            @Valid @RequestBody ActivityDto activityDto
    ) {
        return new ResponseEntity<>(
                this.activityService.saveWithoutPrice(
                        activityDto.ofEntity(),
                        locationId,
                        activityDto.getEmployeeIds()
                ).ofDto(),
                HttpStatus.CREATED
        );
    }

    @GetMapping
    public ResponseEntity<Set<ActivityDto>> getAll(
            @PathVariable("gym_brand_id") UUID gymBrandId,
            @PathVariable("location_id") UUID locationId
    ) {
        return new ResponseEntity<>(
                this.activityService.getAllByLocationId(locationId)
                        .stream().map(Activity::ofDto)
                        .collect(Collectors.toSet()),
                HttpStatus.OK
        );
    }
}
