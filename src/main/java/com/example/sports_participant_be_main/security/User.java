package com.example.sports_participant_be_main.security;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public class User {

    private String email;
    private String password;
    private Set<Role> roles;
}
