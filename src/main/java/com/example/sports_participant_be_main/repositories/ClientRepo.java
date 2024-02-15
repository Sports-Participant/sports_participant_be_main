package com.example.sports_participant_be_main.repositories;

import com.example.sports_participant_be_main.models.Appointment;
import com.example.sports_participant_be_main.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Repository
public interface ClientRepo extends JpaRepository<Client, UUID> {
    Set<Client> getAllByLocationsIdIn(Collection<UUID> ids);
    void deleteById(UUID id);

    int countClientsByLocationsIdIn(Collection<UUID> ids);

    @Query(value = "select count(cl.id) from clients as cl inner join client_locations as cll on cl.id = cll.client_id inner join locations l on cll.location_id = l.id where l.gym_brand_id = ?1",
            nativeQuery = true)
    int countClientsByGymBrandId(UUID gymBrandId);

    Collection<Client> getClientsByLocationsIdIn(Collection<UUID> ids);

    int countClientsByCreatedAtAfterAndLocationsIdIn(LocalDateTime dateTime, Collection<UUID> ids);
    @Query(value = "select count(cl.id) from clients as cl inner join client_locations as cll on cl.id = cll.client_id inner join locations l on cll.location_id = l.id where l.gym_brand_id = ?2 and cl.created_at > ?1",
            nativeQuery = true)
    int countClientsByCreatedAtAfterAndGymBrandId(LocalDateTime dateTime, UUID gymBrandId);

    @Query(value = "select cl.id, cl.created_at, cl.updated_at, cl.city, cl.country, cl.dob, cl.email, cl.firstname, cl.gender, cl.is_disabled, cl.lastname, cl.password, cl.phone_number, cl.status from clients as cl inner join client_locations as cll on cl.id = cll.client_id inner join locations l on cll.location_id = l.id where l.gym_brand_id = ?1",
            nativeQuery = true)
    List<Client> getClientsByGymBrandId(UUID gymBrandId);
}
