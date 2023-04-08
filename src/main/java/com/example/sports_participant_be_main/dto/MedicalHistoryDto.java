package com.example.sports_participant_be_main.dto;

import com.example.sports_participant_be_main.constants.HealthSupplier;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.UUID;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class MedicalHistoryDto {

    private UUID medicalCardId;

    private UUID clientId;

    private HealthSupplier healthSupplier;

    private Set<MedicalRecordDto> medicalRecords;

    private Set<DisabilityDto> disabilities;
}
