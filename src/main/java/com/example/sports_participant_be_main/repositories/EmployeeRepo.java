package com.example.sports_participant_be_main.repositories;

import com.example.sports_participant_be_main.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, UUID> {
    Optional<Employee> findEmployeeByEmail(String email);
    Set<Employee> getAllByGymBrandId(UUID id);
    Set<Employee> getAllByIdIn(Collection<UUID> ids);
    void deleteById(UUID id);

    @Query("select count(e.id) from Employee as e inner join Location as l on e.gymBrand.id = l.gymBrand.id where l.id = ?1")
    int countEmployeesByLocationId(UUID locationId);

    int countEmployeesByGymBrandId(UUID gymBrandId);

}
