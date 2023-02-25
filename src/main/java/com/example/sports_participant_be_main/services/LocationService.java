package com.example.sports_participant_be_main.services;

import com.example.sports_participant_be_main.models.GymBrand;
import com.example.sports_participant_be_main.models.Location;
import com.example.sports_participant_be_main.models.LocationRoom;
import com.example.sports_participant_be_main.repositories.LocationRepo;
import com.example.sports_participant_be_main.repositories.LocationRoomRepo;
import com.example.sports_participant_be_main.utils.exceptions.gym_brand.GymBrandNotFoundException;
import com.example.sports_participant_be_main.utils.exceptions.location.LocationIsAlreadyExistsException;
import com.example.sports_participant_be_main.utils.exceptions.location.LocationNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
@Slf4j
@AllArgsConstructor
public class LocationService {
    private final LocationRepo locationRepo;
    private final LocationRoomRepo locationRoomRepo;
    private final GymBrandService gymBrandService;

    public Location saveLocation(Location location, UUID gymBrandId, Set<UUID> room_ids) {
        GymBrand gymBrand = gymBrandService.findById(gymBrandId).orElseThrow(() -> {
            throw new GymBrandNotFoundException(gymBrandId);
        });

        Optional<Location> l = locationRepo.findLocationByStreetAndStreetNumber(
                location.getStreet(),
                location.getStreetNumber()
        );

        if (l.isPresent())
            throw new LocationIsAlreadyExistsException(location.getStreet(), location.getStreetNumber());

        location.setRooms(this.findAllLocationRoomsById(room_ids));
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

    public Optional<Location> findLocationById(UUID id) {
        return this.locationRepo.findById(id);
    }

    public LocationRoom saveLocationRoom(LocationRoom locationRoom, UUID locationId) {
        Location location = this.findLocationById(locationId).orElseThrow(() -> {
            throw new LocationNotFoundException(locationId);
        });

        locationRoom.setLocation(location);
        return this.locationRoomRepo.save(locationRoom);
    }

    public Optional<LocationRoom> findLocationRoomById(UUID id) {
        return this.locationRoomRepo.findById(id);
    }

    public Set<LocationRoom> findAllLocationRoomsById(Collection<UUID> ids) {
        Set<LocationRoom> rooms = this.locationRoomRepo.getAllByIdIn(ids);

        if (rooms.size() != ids.size())
            log.warn("The count of location ids and the count of locations not equal.");

        return rooms;
    }
}
