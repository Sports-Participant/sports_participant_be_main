package com.example.sports_participant_be_main.repositories;

import com.example.sports_participant_be_main.models.MedicalCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface MedicalCardRepo extends JpaRepository<MedicalCard, UUID> {

    Optional<MedicalCard> findByClientId(UUID id);
}
