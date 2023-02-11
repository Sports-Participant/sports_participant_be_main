package com.example.sports_participant_be_main.models.dto;

import com.example.sports_participant_be_main.models.Owner;
import com.example.sports_participant_be_main.security.Role;
import lombok.*;

import javax.validation.constraints.Email;
import java.util.Collection;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class OwnerDto {

    private UUID id;

    @NonNull
    private String firstname;

    @NonNull
    private String lastname;

    @NonNull
    @Email(regexp = ".+@.+\\..+", message = "Invalid email format")
    private String email;

    @NonNull
    private String password;

    private String country;

    private String city;

    @NonNull
    private String phoneNumber;

    private Role role;

    private Collection<UUID> gymBrandsId;

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
                .build()
                ;
    }
}
