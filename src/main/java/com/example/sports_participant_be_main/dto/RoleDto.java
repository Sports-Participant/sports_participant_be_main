package com.example.sports_participant_be_main.dto;

import com.example.sports_participant_be_main.models.Role;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class RoleDto {

    private UUID id;

    @NotNull
    private String name;

    public Role ofEntity(){
        return Role.builder()
                .id(this.id)
                .name(this.name)
                .build()
                ;
    }
}
