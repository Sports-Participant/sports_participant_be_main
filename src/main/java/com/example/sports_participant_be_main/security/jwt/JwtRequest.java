package com.example.sports_participant_be_main.security.jwt;

import lombok.Data;

@Data
public class JwtRequest {

    private String email;
    private String password;
}
