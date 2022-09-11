package com.example.sports_participant_be_main.security;

import com.example.sports_participant_be_main.models.Owner;
import com.example.sports_participant_be_main.repositories.OwnerRepo;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class UserService {

    private final OwnerRepo ownerRepo;

    // todo Add Employee repo implementation
    public User getByEmail(@NonNull String email) {
        User user = null;
        if (ownerRepo.findOwnerByEmail(email).isPresent()) {
            Owner owner = ownerRepo.findOwnerByEmail(email).get();
            user = new User(owner.getEmail(), owner.getPassword(), Collections.singleton(owner.getRole()));
        }

        return user;
    }

}
