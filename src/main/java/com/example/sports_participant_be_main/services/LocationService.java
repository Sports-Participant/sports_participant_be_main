package com.example.sports_participant_be_main.services;

import com.example.sports_participant_be_main.models.GymBrand;
import com.example.sports_participant_be_main.models.Location;
import com.example.sports_participant_be_main.repositories.LocationRepo;
import com.example.sports_participant_be_main.utils.ResponseMessages;
import com.example.sports_participant_be_main.utils.exceptions.gym_brand.GymBrandNotFoundException;
import com.example.sports_participant_be_main.utils.exceptions.location.LocationIsAlreadyExistsException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
@AllArgsConstructor
public class LocationService {
    private final LocationRepo locationRepo;
    private final GymBrandService gymBrandService;

    public Location save(Location location, UUID gymBrandId) {
        GymBrand gymBrand = gymBrandService.findById(gymBrandId).orElseThrow(() -> {
            log.error(ResponseMessages.GymBrand.NOT_FOUND.message + " gymBrandId={}", gymBrandId);
            throw new GymBrandNotFoundException();
        });

        Optional<Location> l = locationRepo.findLocationByStreetAndStreetNumber(
                location.getStreet(),
                location.getStreetNumber()
        );

        if (l.isPresent()) {
            log.error(ResponseMessages.Location.LOCATION_EXISTS.message + " locationId={}", l.get().getId());
            throw new LocationIsAlreadyExistsException();
        }

        location.setGymBrand(gymBrand);
        return this.locationRepo.save(location);
    }
}
