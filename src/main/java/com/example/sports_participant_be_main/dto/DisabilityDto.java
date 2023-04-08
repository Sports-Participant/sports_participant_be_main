package com.example.sports_participant_be_main.dto;

import com.example.sports_participant_be_main.models.Disability;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class DisabilityDto {

    private UUID id;

    @NotNull
    private String name;

    @NotNull
    private Disability.Severity severity;

    @NotNull
    private String note;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate onsetDate;

    @NotNull
    private UUID medicalCardId;

    public Disability ofEntity() {
        return Disability.builder()
                .id(this.id)
                .name(this.name)
                .severity(this.severity)
                .note(this.note)
                .onsetDate(this.onsetDate)
                .build()
                ;
    }
}
