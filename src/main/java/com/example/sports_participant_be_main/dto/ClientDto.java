package com.example.sports_participant_be_main.dto;

import com.example.sports_participant_be_main.models.Client;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class ClientDto {

    private UUID id;

    @NotNull
    private String firstname;

    @NotNull
    private String lastname;

    @NotNull
    private String email;

    private String password;

    private String country;

    private String city;

    @NotNull
    private String phoneNumber;

    private Client.Status status;

    private Boolean is_disabled = false;

    private Set<UUID> locationIds = new HashSet<>();

    public Client ofEntity(){
        return Client.builder()
                .id(this.id)
                .firstname(this.firstname)
                .lastname(this.lastname)
                .email(this.email)
                .password(this.password)
                .country(this.country)
                .city(this.city)
                .phoneNumber(this.phoneNumber)
                .status(this.status)
                .is_disabled(this.is_disabled)
                .build()
                ;
    }
}
