package com.example.sports_participant_be_main.models.dto;

import com.example.sports_participant_be_main.models.GymBrand;
import com.example.sports_participant_be_main.models.Owner;
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
    private UUID ownerID;

    public GymBrand ofEntity() {
        return GymBrand.builder()
                .id(this.id)
                .name(this.name)
                .build()
                ;
    }
}
