package com.example.sports_participant_be_main.services;

import com.example.sports_participant_be_main.models.GymBrand;
import com.example.sports_participant_be_main.repositories.GymBrandRepo;
import com.example.sports_participant_be_main.utils.exceptions.gym_brand.GymBrandIsAlreadyExistsException;
import com.example.sports_participant_be_main.utils.exceptions.owner.OwnerNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class GymBrandService {

    private final GymBrandRepo gymBrandRepo;
    private final OwnerService ownerService;

    public GymBrand save(GymBrand gymBrand, UUID ownerId) {
        if (gymBrandRepo.findByName(gymBrand.getName()).isPresent())
            throw new GymBrandIsAlreadyExistsException(gymBrand.getName());

        gymBrand.setOwner(ownerService.
                findById(ownerId)
                .orElseThrow(() -> {
                    throw new OwnerNotFoundException(ownerId);
                }));

        gymBrand.setStatus(GymBrand.Status.ACTIVE);
        return gymBrandRepo.save(gymBrand);
    }

    public Collection<GymBrand> getAllByOwnerId(UUID ownerId) {
        return this.gymBrandRepo.getAllByOwnerId(ownerId);
    }

    public Optional<GymBrand> findById(UUID id) {
        return this.gymBrandRepo.findById(id);
    }

    public void delete(UUID id) {
        this.gymBrandRepo.deleteById(id);
    }

    public Optional<GymBrand> findByName(String name) {
        return this.gymBrandRepo.findByName(name);
    }
}
