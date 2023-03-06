package com.example.sports_participant_be_main.services;

import com.example.sports_participant_be_main.models.GymBrand;
import com.example.sports_participant_be_main.models.Owner;
import com.example.sports_participant_be_main.models.Role;
import com.example.sports_participant_be_main.repositories.OwnerRepo;
import com.example.sports_participant_be_main.utils.exceptions.owner.OwnerAlreadyExistsException;
import com.example.sports_participant_be_main.utils.exceptions.owner.OwnerNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

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
        owner.setRole(role);
        owner.setPassword(passwordEncoder.encode(owner.getPassword()));
        return ownerRepo.save(owner);
    }

    public Optional<Owner> findById(UUID ownerId) {
        return this.ownerRepo.findOwnerById(ownerId);
    }

    public Optional<Owner> findOwnerByEmail(String email) {
        return ownerRepo.findOwnerByEmail(email);
    }

    public Set<GymBrand> getAllGymBrandsByOwnerId(UUID id){
        Owner owner = this.findById(id).orElseThrow(() -> {throw new OwnerNotFoundException(id);});
        return owner.getGymBrands();
    }
}
