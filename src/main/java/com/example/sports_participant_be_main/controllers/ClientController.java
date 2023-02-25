package com.example.sports_participant_be_main.controllers;

import com.example.sports_participant_be_main.dto.ClientDto;
import com.example.sports_participant_be_main.services.ClientService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/gym_brands/{gym_brand_id}/clients")
public class ClientController {

    private final ClientService clientService;

    @PostMapping
    public ResponseEntity<ClientDto> add(
            @PathVariable("gym_brand_id") UUID gymBrandId,
            @Valid @RequestBody ClientDto clientDto
    ) {
        return new ResponseEntity<>(
                this.clientService.save(clientDto.ofEntity(), clientDto.getLocation_ids()).ofDto(),
                HttpStatus.CREATED
        );
    }

}
