package com.example.sports_participant_be_main.repositories;

import com.example.sports_participant_be_main.models.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface AppointmentRepo extends JpaRepository<Appointment, UUID> {
    List<Appointment> getAllByDateAndEmployeeIdOrderByStart(LocalDate date, UUID id);
}
