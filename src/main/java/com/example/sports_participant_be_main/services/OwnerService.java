package com.example.sports_participant_be_main.services;

import com.example.sports_participant_be_main.models.Owner;
import com.example.sports_participant_be_main.repositories.OwnerRepo;
import com.example.sports_participant_be_main.security.Role;
import com.example.sports_participant_be_main.utils.ResponseMessages;
import com.example.sports_participant_be_main.utils.exceptions.OwnerNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class OwnerService {

    private final PasswordEncoder passwordEncoder;
    private final OwnerRepo ownerRepo;

    public Owner save(Owner owner) {
        ownerRepo.findOwnerByEmail(owner.getEmail())
                .orElseThrow(() -> {
                    log.error(ResponseMessages.Owner.OWNER_EXISTS.message);
                    throw new OwnerNotFoundException();
                });

        owner.setRole(Role.OWNER);
        owner.setPassword(passwordEncoder.encode(owner.getPassword()));
        return ownerRepo.save(owner);
    }

    public Optional<Owner> getById(UUID ownerId) {
        return this.ownerRepo.findOwnerById(ownerId);
    }

    public Owner getOwnerByEmail(String email) {
        return ownerRepo
                .findOwnerByEmail(email)
                .orElseThrow(() -> {
                    log.error(ResponseMessages.Owner.NOT_FOUND.message);
                    return new OwnerNotFoundException();
                });
    }
}
