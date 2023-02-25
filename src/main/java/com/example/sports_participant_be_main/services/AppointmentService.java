package com.example.sports_participant_be_main.services;

import com.example.sports_participant_be_main.models.*;
import com.example.sports_participant_be_main.repositories.AppointmentRepo;
import com.example.sports_participant_be_main.utils.exceptions.location.LocationNotFoundException;
import com.example.sports_participant_be_main.utils.exceptions.location.location_room.LocationRoomNotFoundException;
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

    public Appointment save(Appointment appointment, UUID locationId, UUID roomId) {
        Optional<Location> location = this.locationService.findLocationById(locationId);

        appointment.setLocation(location.orElseThrow(() -> {
            throw new LocationNotFoundException(locationId);
        }));

        if (roomId != null)
            appointment.setRoom(this.locationService.findLocationRoomById(roomId).orElseThrow(() -> {
                throw new LocationRoomNotFoundException(roomId);
            }));

        appointment.setStatus(Appointment.Status.ACTIVE);
        return this.appointmentRepo.save(appointment);
    }
}
