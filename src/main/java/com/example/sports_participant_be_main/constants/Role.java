package com.example.sports_participant_be_main.constants;

public enum Role {
    OWNER("OWNER"),
    ADMIN("ADMIN"),
    COACH("COACH"),
    RECEPTIONIST("RECEPTIONIST"),
    ;

    public final String message;
    Role(String message) {
        this.message = message;
    }
}
