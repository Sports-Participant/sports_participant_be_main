package com.example.sports_participant_be_main.security.jwt;

import lombok.Data;

@Data
public class RefreshJwtRequest {

    public String refreshToken;
}
