package com.example.sports_participant_be_main.dto;

import com.example.sports_participant_be_main.models.Activity;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.Set;
import java.util.UUID;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class ActivityDto {

    private UUID id;

    @NotNull
    private String title;

    private String description;

    @NotNull
    private UUID locationId;

    private Set<UUID> employeeIds;

    public Activity ofEntity() {
        return Activity.builder()
                .id(this.id)
                .title(this.title)
                .description(this.description)
                .build()
                ;
    }
}
