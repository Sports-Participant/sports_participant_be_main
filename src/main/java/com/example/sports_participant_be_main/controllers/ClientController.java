package com.example.sports_participant_be_main.controllers;

import com.example.sports_participant_be_main.constants.HealthSupplier;
import com.example.sports_participant_be_main.dto.ClientDto;
import com.example.sports_participant_be_main.models.Client;
import com.example.sports_participant_be_main.services.ClientService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/gym_brands/{gym_brand_id}/clients")
public class ClientController {

    private final ClientService clientService;

    @PostMapping("")
    public ResponseEntity<ClientDto> add(
            @PathVariable("gym_brand_id") UUID gymBrandId,
            @Valid @RequestBody ClientDto clientDto,
            @RequestParam("health_supplier") HealthSupplier healthSupplier
            ) {
        return new ResponseEntity<>(
                this.clientService.save(clientDto.ofEntity(), clientDto.getLocationIds(), healthSupplier).ofDto(),
                HttpStatus.CREATED
        );
    }

    @GetMapping("")
    public ResponseEntity<Collection<ClientDto>> getAllByLocationId(
            @PathVariable("gym_brand_id") UUID gymBrandId,
            @RequestParam("location_id") UUID locationId
    ) {
        return new ResponseEntity<>(
                this.clientService.getAllByLocationId(locationId)
                        .stream()
                        .map(Client::ofDto)
                        .collect(Collectors.toSet()),
                HttpStatus.OK
        );
    }

    @DeleteMapping("{client_id}")
    public ResponseEntity<Boolean> delete(
            @PathVariable("gym_brand_id") UUID gymBrandId,
            @PathVariable("client_id") UUID clientId
    ) {
        this.clientService.delete(clientId);
        return new ResponseEntity<>(
                true,
                HttpStatus.OK
        );
    }
}
