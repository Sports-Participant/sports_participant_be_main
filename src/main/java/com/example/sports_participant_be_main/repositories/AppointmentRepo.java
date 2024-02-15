package com.example.sports_participant_be_main.repositories;

import com.example.sports_participant_be_main.models.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface AppointmentRepo extends JpaRepository<Appointment, UUID> {
    List<Appointment> getAllByDateAndEmployeeIdOrderByStart(LocalDate date, UUID id);
    List<Appointment> getAllByDateOrderByStart(LocalDate date);
    void deleteById(UUID id);

    int countAppointmentsByLocationId(UUID locationId);

    @Query("select count(a.id) from Appointment as a inner join Location as l on a.location.id = l.id where l.gymBrand.id = ?1")
    int countAppointmentsByGymBrandId(UUID gymBrandId);

    int countAppointmentsByCreatedAtAfterAndLocationId(LocalDateTime dateTime, UUID locationId);

    @Query("select count(a.id) from Appointment as a inner join Location as l on a.location.id = l.id where l.gymBrand.id = ?2 and a.createdAt > ?1")
    int countAppointmentsByCreatedAtAfterAndGymBrandId(LocalDateTime dateTime, UUID gymBrandId);

    List<Appointment> getAppointmentsByLocationId(UUID id);
    @Query("select a from Appointment as a inner join Location as l on a.location.id = l.id where l.gymBrand.id = ?1")
    List<Appointment> getAppointmentsByGymBrandId(UUID gymBrandId);
}
