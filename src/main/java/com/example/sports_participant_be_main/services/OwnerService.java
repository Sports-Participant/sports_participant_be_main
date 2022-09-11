package com.example.sports_participant_be_main.services;

import com.example.sports_participant_be_main.models.Owner;
import com.example.sports_participant_be_main.repositories.OwnerRepo;
import com.example.sports_participant_be_main.security.Role;
import com.example.sports_participant_be_main.utils.ResponseMessages;
import com.example.sports_participant_be_main.utils.exceptions.OwnerNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OwnerService {

    private final OwnerRepo ownerRepo;

    public Owner save(Owner owner) {
        if (ownerRepo.findOwnerByEmail(owner.getEmail()).isPresent()) {
            System.out.println("User is already exists");
            return null;
        }
        owner.setRole(Role.OWNER);
        return ownerRepo.save(owner);
    }

    public Owner getOwnerByEmail(String email) {
        return ownerRepo
                .findOwnerByEmail(email)
                .orElseThrow(() -> new OwnerNotFoundException(ResponseMessages.Owner.NOT_FOUND.message));
    }
}
