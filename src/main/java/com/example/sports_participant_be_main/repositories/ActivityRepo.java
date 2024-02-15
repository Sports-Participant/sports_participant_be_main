package com.example.sports_participant_be_main.repositories;

import com.example.sports_participant_be_main.models.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;
import java.util.UUID;

@Repository
public interface ActivityRepo extends JpaRepository<Activity, UUID> {
    Set<Activity> findAllByLocationId(UUID id);
    void deleteById(UUID id);
    int countActivitiesByLocationId(UUID locationId);

    @Query("select count(a.id) from Activity as a inner join Location as l on a.location.id = l.id where l.gymBrand.id = ?1")
    int countActivitiesByGymBrandId(UUID gymBrandId);
}
