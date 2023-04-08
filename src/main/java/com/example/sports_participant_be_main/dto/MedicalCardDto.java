package com.example.sports_participant_be_main.dto;

import com.example.sports_participant_be_main.models.MedicalCard;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class MedicalCardDto {

    private UUID id;

    @NotNull
    private UUID clientId;

    public MedicalCard ofEntity() {
        return MedicalCard.builder()
                .id(this.id)
                .build()
                ;
    }
}
