package com.example.sports_participant_be_main.controllers;

import com.example.sports_participant_be_main.dto.ScheduleDto;
import com.example.sports_participant_be_main.services.ScheduleService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/owners/gym_brands/{gym_brand_id}/locations/{location_id}/schedules")
@AllArgsConstructor
@Slf4j
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping
    public ResponseEntity<ScheduleDto> add(
            @PathVariable("gym_brand_id") UUID gymBrandId,
            @PathVariable("location_id") UUID locationId,
            @Valid @RequestBody ScheduleDto scheduleDto
    ) {
        return new ResponseEntity<>(
                this.scheduleService.save(scheduleDto.ofEntity(), scheduleDto.getLocation_id()).ofDto(),
                HttpStatus.CREATED
        );
    }
}
