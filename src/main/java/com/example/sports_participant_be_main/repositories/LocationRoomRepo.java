package com.example.sports_participant_be_main.repositories;

import com.example.sports_participant_be_main.models.LocationRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Set;
import java.util.UUID;

@Repository
public interface LocationRoomRepo extends JpaRepository<LocationRoom, UUID> {
    Set<LocationRoom> getAllByIdIn(Collection<UUID> ids);
    Set<LocationRoom> getAllByLocationId(UUID id);
    void deleteById(UUID id);

    int countLocationRoomsByLocationId(UUID locationId);

    @Query("select count(a.id) from LocationRoom as a inner join Location as l on a.location.id = l.id where l.gymBrand.id = ?1")
    int countLocationRoomsByGymBrandId(UUID gymBrandId);
}
