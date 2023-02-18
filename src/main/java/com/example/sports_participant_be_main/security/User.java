package com.example.sports_participant_be_main.security;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
public class User {

    private UUID id;
    private String email;
    private String password;
    private Set<Role> roles;
}
