package com.example.sports_participant_be_main.dto;

import com.example.sports_participant_be_main.models.Owner;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class OwnerDto {

    private UUID id;

    @NotNull
    private String firstname;

    @NotNull
    private String lastname;

    @NotNull
    @Email(regexp = ".+@.+\\..+", message = "Invalid email format")
    private String email;

    @NotNull
    private String password;

    private String country;

    private String city;

    @NotNull
    private String phoneNumber;

    private Owner.Status status;

    public Owner ofEntity(){
        return Owner.builder()
                .id(this.id)
                .firstname(this.firstname)
                .lastname(this.lastname)
                .email(this.email)
                .password(this.password)
                .country(this.country)
                .city(this.city)
                .phoneNumber(this.phoneNumber)
                .status(this.status)
                .build()
                ;
    }
}
