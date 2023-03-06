package com.example.sports_participant_be_main.repositories;

import com.example.sports_participant_be_main.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Repository
public interface RoleRepo extends JpaRepository<Role, UUID> {
    Optional<Role> findByName(String name);
    Set<Role> findRoleByIdIn(Set<UUID> ids);

    @Query( value = "select * from roles where id in " +
            "(select * from roles as r" +
            "inner join user_roles as ur on on r.id = ur.role_id" +
            "inner join employee as e on ur.user_id = e.id" +
            "where e.id = :id" +
            ");" , nativeQuery = true)
    Set<Role> getRolesByEmployeeId(@Param("id") UUID id);
}
