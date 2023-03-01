package com.example.sports_participant_be_main.services;

import com.example.sports_participant_be_main.models.Owner;
import com.example.sports_participant_be_main.models.Role;
import com.example.sports_participant_be_main.repositories.OwnerRepo;
import com.example.sports_participant_be_main.utils.exceptions.owner.OwnerAlreadyExistsException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
@Slf4j
public class OwnerService {

    private final PasswordEncoder passwordEncoder;
    private final OwnerRepo ownerRepo;
    private final RoleService roleService;

    public Owner save(Owner owner) {
        ownerRepo.findOwnerByEmail(owner.getEmail())
                .ifPresent((item) -> {
                    throw new OwnerAlreadyExistsException(owner.getEmail());
                });

        Role role = this.roleService.findByName("OWNER").orElseThrow(() -> {
            throw new RuntimeException();
        });
        owner.setRoles(Stream.of(role).collect(Collectors.toSet()));
        owner.setPassword(passwordEncoder.encode(owner.getPassword()));
        return ownerRepo.save(owner);
    }

    public Optional<Owner> getById(UUID ownerId) {
        return this.ownerRepo.findOwnerById(ownerId);
    }

    public Optional<Owner> findOwnerByEmail(String email) {
        return ownerRepo.findOwnerByEmail(email);
    }
}
