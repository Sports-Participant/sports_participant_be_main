package com.example.sports_participant_be_main.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@RequiredArgsConstructor
public enum Role implements GrantedAuthority {

    ADMIN("ADMIN"),
    COACH("COACH"),
    OWNER("OWNER");

    private final String vale;

    @Override
    public String getAuthority() {
        return vale;
    }

}
