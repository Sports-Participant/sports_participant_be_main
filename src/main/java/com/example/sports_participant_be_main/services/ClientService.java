package com.example.sports_participant_be_main.services;

import com.example.sports_participant_be_main.constants.HealthSupplier;
import com.example.sports_participant_be_main.models.Client;
import com.example.sports_participant_be_main.models.Location;
import com.example.sports_participant_be_main.repositories.ClientRepo;
import com.example.sports_participant_be_main.services.medical_history.IMedicalSupplier;
import com.example.sports_participant_be_main.services.medical_history.MedicalHistoryFactory;
import com.example.sports_participant_be_main.utils.exceptions.location.AnyLocationNotFoundException;
import com.example.sports_participant_be_main.utils.exceptions.location.LocationNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class ClientService {

    private final ClientRepo clientRepo;
    private final LocationService locationService;
    private final MedicalHistoryFactory medicalHistoryFactory;

    public Client save(Client client, Set<UUID> locationIds, HealthSupplier healthSupplier) {
        Set<Location> locations = this.locationService.getLocationsByIds(locationIds);

        if (locations.isEmpty()) throw new AnyLocationNotFoundException(locationIds);

        if (client.getIsDisabled() && healthSupplier != HealthSupplier.NONE) {
            IMedicalSupplier medicalHistory = medicalHistoryFactory.getMedicalHistoryService(healthSupplier);
            medicalHistory.save(client);
        }

        client.setLocations(locations);
        client.setStatus(Client.Status.ACTIVE);
        return this.clientRepo.save(client);
    }

    public Set<Client> getAllByLocationId(UUID locationId) {
        Location location = this.locationService.findLocationById(locationId).orElseThrow(() -> {
            throw new LocationNotFoundException(locationId);
        });

        return this.clientRepo.getAllByLocationsIdIn(Set.of(location.getId()));
    }

    public Optional<Client> findById(UUID clientId) {
        return this.clientRepo.findById(clientId);
    }
}
