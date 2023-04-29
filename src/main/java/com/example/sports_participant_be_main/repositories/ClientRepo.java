package com.example.sports_participant_be_main.repositories;

import com.example.sports_participant_be_main.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Set;
import java.util.UUID;

@Repository
public interface ClientRepo extends JpaRepository<Client, UUID> {
    Set<Client> getAllByLocationsIdIn(Collection<UUID> ids);
    void deleteById(UUID id);
}
