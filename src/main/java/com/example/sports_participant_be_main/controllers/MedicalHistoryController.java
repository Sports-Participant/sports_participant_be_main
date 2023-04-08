package com.example.sports_participant_be_main.controllers;

import com.example.sports_participant_be_main.dto.MedicalHistoryDto;
import com.example.sports_participant_be_main.services.medical_history.MedicalHistoryService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/gym_brands/{gym_brand_id}/clients/{client_id}/medical_history")
public class MedicalHistoryController {

    private final MedicalHistoryService medicalHistoryService;

    @GetMapping
    public ResponseEntity<Optional<MedicalHistoryDto>> get(
            @PathVariable("gym_brand_id") UUID gymBrandId,
            @PathVariable("client_id") UUID clientId
    ) {
        return new ResponseEntity<>(
                this.medicalHistoryService.getMedicalHistoryByClientId(clientId),
                HttpStatus.OK
        );
    }
}
