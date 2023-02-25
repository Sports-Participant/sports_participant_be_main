package com.example.sports_participant_be_main.services;

import com.example.sports_participant_be_main.models.GymBrand;
import com.example.sports_participant_be_main.models.Location;
import com.example.sports_participant_be_main.repositories.LocationRepo;
import com.example.sports_participant_be_main.utils.exceptions.gym_brand.GymBrandNotFoundException;
import com.example.sports_participant_be_main.utils.exceptions.location.LocationIsAlreadyExistsException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
@Slf4j
@AllArgsConstructor
public class LocationService {
    private final LocationRepo locationRepo;
    private final GymBrandService gymBrandService;

    public Location save(Location location, UUID gymBrandId) {
        GymBrand gymBrand = gymBrandService.findById(gymBrandId).orElseThrow(() -> {
            throw new GymBrandNotFoundException(gymBrandId);
        });

        Optional<Location> l = locationRepo.findLocationByStreetAndStreetNumber(
                location.getStreet(),
                location.getStreetNumber()
        );

        if (l.isPresent())
            throw new LocationIsAlreadyExistsException(location.getStreet(), location.getStreetNumber());


        location.setGymBrand(gymBrand);
        return this.locationRepo.save(location);
    }

    public Set<Location> getLocationsByIds(Set<UUID> location_ids) {
        Set<Location> locations = this.locationRepo.findLocationsByIdIn(location_ids);

        if (locations.size() != location_ids.size()){
            log.warn("The count of location ids and the count of locations not equal.");
        }

        return locations;
    }

    public Optional<Location> findById(UUID id) {
        return this.locationRepo.findById(id);
    }
}
