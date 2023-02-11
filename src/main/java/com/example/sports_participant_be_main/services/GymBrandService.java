package com.example.sports_participant_be_main.services;

import com.example.sports_participant_be_main.models.GymBrand;
import com.example.sports_participant_be_main.repositories.GymBrandRepo;
import com.example.sports_participant_be_main.repositories.OwnerRepo;
import com.example.sports_participant_be_main.utils.ResponseMessages;
import com.example.sports_participant_be_main.utils.exceptions.GymBrandHasAlreadyExistsException;
import com.example.sports_participant_be_main.utils.exceptions.OwnerNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class GymBrandService {

    private final GymBrandRepo gymBrandRepo;
    private final OwnerRepo ownerRepo;

    public GymBrand save(GymBrand gymBrand, UUID ownerId) {
        if (gymBrandRepo.findByName(gymBrand.getName()).isPresent()) {
            log.error(ResponseMessages.GymBrand.GYM_BRAND_EXISTS.message + " ownerID={}", ownerId);
            throw new GymBrandHasAlreadyExistsException();
        }

        gymBrand.setOwner(ownerRepo.
                findOwnerById(ownerId)
                .orElseThrow(() -> {
                    log.error(ResponseMessages.Owner.NOT_FOUND.message + " ownerID={}", ownerId);
                    throw new OwnerNotFoundException();
                }));

        return gymBrandRepo.save(gymBrand);
    }

    public Collection<GymBrand> getAllByOwnerId(UUID ownerId) {
        return this.gymBrandRepo.getAllByOwnerId(ownerId);
    }
}
