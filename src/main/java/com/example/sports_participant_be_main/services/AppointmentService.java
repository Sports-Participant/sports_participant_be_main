package com.example.sports_participant_be_main.services;

import com.example.sports_participant_be_main.models.*;
import com.example.sports_participant_be_main.repositories.AppointmentRepo;
import com.example.sports_participant_be_main.utils.exceptions.location.LocationNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class AppointmentService {

    private final LocationService locationService;
    private final AppointmentRepo appointmentRepo;

    public Appointment save(Appointment appointment, UUID location_id) {
        Optional<Location> location = this.locationService.findById(location_id);

        appointment.setLocation(location.orElseThrow(() -> {
            throw new LocationNotFoundException(location_id);
        }));

        return this.appointmentRepo.save(appointment);
    }
}
