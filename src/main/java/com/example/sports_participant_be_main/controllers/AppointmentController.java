package com.example.sports_participant_be_main.controllers;

import com.example.sports_participant_be_main.dto.AppointmentDto;
import com.example.sports_participant_be_main.services.AppointmentService;
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
@RequestMapping("/gym_brands/{gym_brand_id}/locations/{location_id}/appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;

    @PostMapping
    public ResponseEntity<AppointmentDto> add(
            @PathVariable("gym_brand_id") UUID gymBrandId,
            @PathVariable("location_id") UUID locationId,
            @Valid @RequestBody AppointmentDto appointmentDto
    ) {
        return new ResponseEntity<>(
                this.appointmentService.save(appointmentDto.ofEntity(), locationId, appointmentDto.getRoom_id()).ofDto(),
                HttpStatus.CREATED
        );
    }


}
