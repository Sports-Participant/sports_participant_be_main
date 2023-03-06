package com.example.sports_participant_be_main.repositories;

import com.example.sports_participant_be_main.models.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Repository
public interface LocationRepo extends JpaRepository<Location, UUID> {
    Optional<Location> findLocationByStreetAndStreetNumber(String street, Integer streetNumber);
    Set<Location> findLocationsByIdIn(Set<UUID> ids);

    Set<Location> getAllByGymBrandId(UUID id);
}
