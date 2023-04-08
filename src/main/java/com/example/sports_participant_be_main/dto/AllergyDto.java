package com.example.sports_participant_be_main.dto;

import com.example.sports_participant_be_main.models.Allergy;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class AllergyDto {

    private UUID id;

    @NotNull
    private String name;

    private Allergy.Type type;

    private List<String> symptoms = new ArrayList<>();

    @NotNull
    private Allergy.Severity severity;

    @NotNull
    private String note;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate onsetDate;

    @NotNull
    private UUID medicalCardId;

    public Allergy ofEntity() {
        return Allergy.builder()
                .id(this.id)
                .name(this.name)
                .type(this.type)
                .symptoms(this.symptoms)
                .severity(this.severity)
                .note(this.note)
                .onsetDate(this.onsetDate)
                .build()
                ;
    }
}
