package com.example.sports_participant_be_main.models.dto;

import com.example.sports_participant_be_main.security.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class OwnerDto {
    private UUID id;
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private String country;
    private String city;
    private String phoneNumber;
    private Role role;
}
