package com.example.sports_participant_be_main.repositories;

import com.example.sports_participant_be_main.models.MedicalCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface MedicalCardRepo extends JpaRepository<MedicalCard, UUID> {

    Optional<MedicalCard> findByClientId(UUID id);

    int countMedicalCardsByClientIdIn(Collection<UUID> ids);
    int countMedicalCardsByClientIdInAndCreatedAtAfter(Collection<UUID> ids, LocalDateTime dateTime);

    List<MedicalCard> findAllByClientIdIn(Collection<UUID> ids);
}
