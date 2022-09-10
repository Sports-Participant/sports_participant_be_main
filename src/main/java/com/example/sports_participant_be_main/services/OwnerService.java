package com.example.sports_participant_be_main.services;

import com.example.sports_participant_be_main.models.Owner;
import com.example.sports_participant_be_main.repositories.OwnerRepo;
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
        return ownerRepo.save(owner);
    }
}
