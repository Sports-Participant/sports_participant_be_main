package com.example.sports_participant_be_main.repositories;

import com.example.sports_participant_be_main.models.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;
import java.util.UUID;

@Repository
public interface ActivityRepo extends JpaRepository<Activity, UUID> {
    Set<Activity> findAllByLocationId(UUID id);
}
