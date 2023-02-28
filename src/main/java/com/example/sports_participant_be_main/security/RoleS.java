package com.example.sports_participant_be_main.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@RequiredArgsConstructor
public class RoleS implements GrantedAuthority {

    private final String value;

    @Override
    public String getAuthority() {
        return value;
    }

}
