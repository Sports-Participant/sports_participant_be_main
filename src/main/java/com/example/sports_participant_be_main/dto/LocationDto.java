package com.example.sports_participant_be_main.dto;

import com.example.sports_participant_be_main.models.Location;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class LocationDto {

    private UUID id;

    @NotNull
    private String street;

    @NotNull
    private Integer streetNumber;

    @NotNull
    private Integer capacity;

    @NotNull
    private UUID gymBrandId;

    public Location ofEntity() {
        return Location.builder()
                .id(this.id)
                .street(this.street)
                .streetNumber(streetNumber)
                .capacity(this.capacity)
                .build()
                ;
    }
}
