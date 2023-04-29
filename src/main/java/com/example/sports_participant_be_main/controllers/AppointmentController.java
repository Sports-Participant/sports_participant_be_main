package com.example.sports_participant_be_main.controllers;

import com.example.sports_participant_be_main.dto.AppointmentDto;
import com.example.sports_participant_be_main.dto.HourDto;
import com.example.sports_participant_be_main.models.Appointment;
import com.example.sports_participant_be_main.services.AppointmentService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/gym_brands/{gym_brand_id}/locations/{location_id}")
public class AppointmentController {

    private final AppointmentService appointmentService;

    @PostMapping("/appointments")
    public ResponseEntity<AppointmentDto> add(
            @PathVariable("gym_brand_id") UUID gymBrandId,
            @PathVariable("location_id") UUID locationId,
            @Valid @RequestBody AppointmentDto appointmentDto
    ) {
        return new ResponseEntity<>(
                this.appointmentService.save(
                        appointmentDto.ofEntity(),
                        locationId,
                        appointmentDto.getRoomId(),
                        appointmentDto.getEmployeeId(),
                        appointmentDto.getActivityId()
                ).ofDto(),
                HttpStatus.CREATED
        );
    }

    @GetMapping("/appointments_available_hours")
    public ResponseEntity<Collection<HourDto>> getAvailableHours(
            @PathVariable("gym_brand_id") UUID gymBrandId,
            @PathVariable("location_id") UUID locationId,
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            @RequestParam("coachId") UUID coachId
    ) {
        return new ResponseEntity<>(
                this.appointmentService.getListOfAvailableHours(date, coachId, locationId),
                HttpStatus.OK
        );
    }

    @GetMapping("/appointments")
    public ResponseEntity<Collection<AppointmentDto>> getAppointmentsByDate(
            @PathVariable("gym_brand_id") UUID gymBrandId,
            @PathVariable("location_id") UUID locationId,
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            @RequestParam("coachId") UUID coachId
    ) {
        return new ResponseEntity<>(
                this.appointmentService.getAllAppointmentsByDateAndEmployeeId(date, coachId)
                        .stream().map(Appointment::ofDto).collect(Collectors.toList()),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/appointments/{appointment_id}")
    public ResponseEntity<Boolean> delete(
            @PathVariable("gym_brand_id") UUID gymBrandId,
            @PathVariable("location_id") UUID locationId,
            @PathVariable("appointment_id") UUID appointmentId
    ) {
        this.appointmentService.delete(appointmentId);
        return new ResponseEntity<>(
                true,
                HttpStatus.OK
        );
    }
}
