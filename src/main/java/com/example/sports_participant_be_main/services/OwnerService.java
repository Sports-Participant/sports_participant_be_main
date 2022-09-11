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

@Service
@AllArgsConstructor
@Slf4j
public class OwnerService {

    private final PasswordEncoder passwordEncoder;
    private final OwnerRepo ownerRepo;

    public Owner save(Owner owner) {
        if (ownerRepo.findOwnerByEmail(owner.getEmail()).isPresent()) {
            log.error(ResponseMessages.Owner.OWNER_EXISTS.message);
            throw new OwnerNotFoundException(ResponseMessages.Owner.OWNER_EXISTS.message);
        }
        owner.setRole(Role.OWNER);
        owner.setPassword(passwordEncoder.encode(owner.getPassword()));
        return ownerRepo.save(owner);
    }

    public Owner getOwnerByEmail(String email) {
        return ownerRepo
                .findOwnerByEmail(email)
                .orElseThrow(() -> {
                    log.error(ResponseMessages.Owner.NOT_FOUND.message);
                    return new OwnerNotFoundException(ResponseMessages.Owner.NOT_FOUND.message);
                });
    }
}
