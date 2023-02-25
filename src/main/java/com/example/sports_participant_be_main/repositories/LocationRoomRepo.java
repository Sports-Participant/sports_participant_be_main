package com.example.sports_participant_be_main.repositories;

import com.example.sports_participant_be_main.models.LocationRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Set;
import java.util.UUID;

@Repository
public interface LocationRoomRepo extends JpaRepository<LocationRoom, UUID> {
    Set<LocationRoom> getAllByIdIn(Collection<UUID> ids);
}
