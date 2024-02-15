package com.example.sports_participant_be_main.controllers;

import com.example.sports_participant_be_main.dto.StatisticsDto;
import com.example.sports_participant_be_main.services.StatisticsService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/gym_brands/{gym_brand_id}/locations/{location_id}/statistics")
public class StatisticsController {

    private final StatisticsService statisticsService;

    @GetMapping
    public ResponseEntity<StatisticsDto> add(
            @PathVariable("gym_brand_id") UUID gymBrandId,
            @PathVariable("location_id") UUID locationId
    ) {
        return new ResponseEntity<>(
                this.statisticsService.getStatistics(gymBrandId, locationId),
                HttpStatus.CREATED
        );
    }
}
