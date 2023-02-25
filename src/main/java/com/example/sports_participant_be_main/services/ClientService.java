package com.example.sports_participant_be_main.services;

import com.example.sports_participant_be_main.models.Client;
import com.example.sports_participant_be_main.models.Location;
import com.example.sports_participant_be_main.repositories.ClientRepo;
import com.example.sports_participant_be_main.utils.exceptions.location.AnyLocationNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class ClientService {

    private final ClientRepo clientRepo;
    private final LocationService locationService;

    public Client save(Client client, Set<UUID> location_ids) {
        Set<Location> locations = this.locationService.getLocationsByIds(location_ids);

        if (locations.isEmpty()) throw new AnyLocationNotFoundException(location_ids);

        client.setLocations(locations);
        client.setStatus(Client.Status.ACTIVE);
        return this.clientRepo.save(client);
    }
}
