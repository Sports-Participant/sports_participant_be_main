package com.example.sports_participant_be_main.repositories;

import com.example.sports_participant_be_main.models.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Repository
public interface ScheduleRepo extends JpaRepository<Schedule, UUID> {
    Optional<Schedule> findByLocationIdAndDay(UUID locationId, Schedule.Day day);
}
