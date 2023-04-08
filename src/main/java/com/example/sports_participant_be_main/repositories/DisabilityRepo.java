package com.example.sports_participant_be_main.repositories;

import com.example.sports_participant_be_main.models.Disability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DisabilityRepo extends JpaRepository<Disability, UUID> {
}
