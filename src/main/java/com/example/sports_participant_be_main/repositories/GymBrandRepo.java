package com.example.sports_participant_be_main.repositories;

import com.example.sports_participant_be_main.models.GymBrand;
import com.example.sports_participant_be_main.models.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface GymBrandRepo extends JpaRepository<GymBrand, UUID> {
    Optional<GymBrand> findByOwnerId(UUID ownerID);
}
