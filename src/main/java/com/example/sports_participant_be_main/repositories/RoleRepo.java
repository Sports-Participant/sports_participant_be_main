package com.example.sports_participant_be_main.repositories;

import com.example.sports_participant_be_main.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Repository
public interface RoleRepo extends JpaRepository<Role, UUID> {
    Optional<Role> findByName(String name);
    Set<Role> findRoleByIdIn(Set<UUID> ids);
}
