package com.example.sports_participant_be_main.dto;

import com.example.sports_participant_be_main.models.Illness;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class IllnessDto {

    private UUID id;

    @NotNull
    private String name;

    private Illness.Type type;

    private List<String> symptoms = new ArrayList<>();

    @NotNull
    private Illness.Severity severity;

    @NotNull
    private String note;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate onsetDate;

    private Illness.Status status;

    @NotNull
    private UUID medicalCardId;

    public Illness ofEntity() {
        return Illness.builder()
                .id(this.id)
                .name(this.name)
                .type(this.type)
                .status(this.status)
                .symptoms(this.symptoms)
                .severity(this.severity)
                .note(this.note)
                .onsetDate(this.onsetDate)
                .build()
                ;
    }
}
