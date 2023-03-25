package com.example.sports_participant_be_main.controllers;

import com.example.sports_participant_be_main.dto.ScheduleDto;
import com.example.sports_participant_be_main.models.Schedule;
import com.example.sports_participant_be_main.services.ScheduleService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/gym_brands/{gym_brand_id}/locations/{location_id}/schedules")
@AllArgsConstructor
@Slf4j
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping
    public ResponseEntity<Collection<ScheduleDto>> add(
            @PathVariable("gym_brand_id") UUID gymBrandId,
            @PathVariable("location_id") UUID locationId,
            @Valid @RequestBody Set<ScheduleDto> scheduleDtos
    ) {
        return new ResponseEntity<>(
                this.scheduleService.saveAll(scheduleDtos
                                        .stream()
                                        .map(ScheduleDto::ofEntity)
                                        .collect(Collectors.toSet()),
                                locationId).
                        stream().
                        map(Schedule::ofDto)
                        .collect(Collectors.toSet()),
                HttpStatus.CREATED
        );
    }
}
