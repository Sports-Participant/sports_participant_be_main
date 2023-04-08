package com.example.sports_participant_be_main.dto;

import com.example.sports_participant_be_main.models.MedicalRecord;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class MedicalRecordDto {

    private UUID id;

    private Integer recordNumber;

    @NotNull
    private String title;

    @NotNull
    private String text;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate date;

    private List<String> currentMedications;

    @NotNull
    private UUID medicalCardId;

    public MedicalRecord ofEntity() {
        return MedicalRecord.builder()
                .id(this.id)
                .recordNumber(this.recordNumber)
                .title(this.title)
                .text(this.text)
                .date(this.date)
                .currentMedications(this.currentMedications)
                .build()
                ;
    }
}
