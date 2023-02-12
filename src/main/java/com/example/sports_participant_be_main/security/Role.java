package com.example.sports_participant_be_main.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@RequiredArgsConstructor
public enum Role implements GrantedAuthority {

    ADMIN("ADMIN"),
    COACH("COACH"),
    OWNER("OWNER"),
    RECEPTIONIST("RECEPTIONIST"),
    ;

    private final String value;

    @Override
    public String getAuthority() {
        return value;
    }

}
