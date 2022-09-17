package com.example.sports_participant_be_main.services;

import com.example.sports_participant_be_main.models.GymBrand;
import com.example.sports_participant_be_main.models.Owner;
import com.example.sports_participant_be_main.models.dto.GymBrandDto;
import com.example.sports_participant_be_main.repositories.GymBrandRepo;
import com.example.sports_participant_be_main.repositories.OwnerRepo;
import com.example.sports_participant_be_main.utils.ResponseMessages;
import com.example.sports_participant_be_main.utils.exceptions.GymBrandHasAlreadyExistsException;
import com.example.sports_participant_be_main.utils.exceptions.OwnerNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class GymBrandService {

    private final GymBrandRepo gymBrandRepo;
    private final OwnerRepo ownerRepo;

    public GymBrand save(GymBrandDto gymBrandDto) {
        GymBrand gymBrand = gymBrandDto.ofEntity();

        if (gymBrandRepo.findByOwnerId(gymBrandDto.getOwnerID()).isPresent()) {
            log.error(ResponseMessages.GymBrand.GYM_BRAND_EXISTS.message + " ownerID={}", gymBrandDto.getOwnerID());
            throw new GymBrandHasAlreadyExistsException(ResponseMessages.GymBrand.GYM_BRAND_EXISTS.message);
        }

        gymBrand.setOwner(ownerRepo.
                findOwnerById(gymBrandDto.getOwnerID())
                .orElseThrow(() -> {
                    log.error(ResponseMessages.Owner.NOT_FOUND.message + " ownerID={}", gymBrandDto.getOwnerID());
                    throw new OwnerNotFoundException(ResponseMessages.Owner.NOT_FOUND.message);
                }));

        return gymBrandRepo.save(gymBrand);
    }
}
