package com.example.sports_participant_be_main.dto;

import com.example.sports_participant_be_main.models.Client;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
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

    private Boolean isDisabled = false;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dob;

    @NotNull
    private Client.Gender gender;

    private Set<UUID> locationIds = new HashSet<>();

    private UUID medicalCardId;

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
                .isDisabled(this.isDisabled)
                .dob(this.dob)
                .gender(this.gender)
                .build()
                ;
    }
}
