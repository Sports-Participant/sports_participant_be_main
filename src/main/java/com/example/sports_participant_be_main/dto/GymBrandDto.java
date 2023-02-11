package com.example.sports_participant_be_main.dto;

import com.example.sports_participant_be_main.models.GymBrand;
import lombok.*;

import java.util.UUID;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class GymBrandDto {

    private UUID id;

    @NonNull
    private String name;

    @NonNull
    private UUID ownerId;

    public GymBrand ofEntity() {
        return GymBrand.builder()
                .id(this.id)
                .name(this.name)
                .build()
                ;
    }
}
