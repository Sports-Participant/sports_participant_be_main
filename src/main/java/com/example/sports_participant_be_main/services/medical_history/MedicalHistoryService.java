package com.example.sports_participant_be_main.services.medical_history;

import com.example.sports_participant_be_main.dto.MedicalHistoryDto;
import com.example.sports_participant_be_main.models.Client;
import com.example.sports_participant_be_main.models.Disability;
import com.example.sports_participant_be_main.models.MedicalCard;
import com.example.sports_participant_be_main.models.MedicalRecord;
import com.example.sports_participant_be_main.repositories.*;
import com.example.sports_participant_be_main.services.ClientService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
// todo Переробити так, щоб саплаєри використовували цей сервіс, щоб зберігати медичні історії
public class MedicalHistoryService {

    private AllergyRepo allergyRepo;
    private DisabilityRepo disabilityRepo;
    private IllnessRepo illnessRepo;
    private MedicalCardRepo medicalCardRepo;
    private MedicalRecordRepo medicalRecordRepo;
    private ClientService clientService;

    public Optional<MedicalHistoryDto> getMedicalHistoryByClientId(UUID clientId) {
        Client client = this.clientService.findById(clientId).orElseThrow(() -> {
            throw new RuntimeException("Client not found");
        });

        Optional<MedicalCard> medicalCard = this.medicalCardRepo.findByClientId(client.getId());

        if (medicalCard.isEmpty()) return Optional.empty();

        MedicalHistoryDto medicalHistory = MedicalHistoryDto
                .builder()
                .medicalCardId(medicalCard.get().getId())
                .clientId(client.getId())
                .disabilities(medicalCard.get()
                        .getDisabilities()
                        .stream()
                        .map(Disability::ofDto)
                        .collect(Collectors.toSet()))
                .healthSupplier(medicalCard.get().getHealthSupplier())
                .medicalRecords(medicalCard.get()
                        .getMedicalRecords()
                        .stream()
                        .map(MedicalRecord::ofDto)
                        .collect(Collectors.toSet()))
                .build();

        return Optional.of(medicalHistory);
    }
}
