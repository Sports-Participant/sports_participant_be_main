package com.example.sports_participant_be_main.dto;

import com.example.sports_participant_be_main.models.Owner;
import com.example.sports_participant_be_main.security.Role;
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

    private Role role;

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
                .role(this.role)
                .status(this.status)
                .build()
                ;
    }
}
