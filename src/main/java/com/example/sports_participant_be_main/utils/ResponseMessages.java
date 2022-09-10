package com.example.sports_participant_be_main.utils;

public class ResponseMessages {

    public enum Owner {
        NOT_FOUND("Owner now found"),
        CREATED("Owner has been created");

        public final String message;
        Owner(String message) {
            this.message = message;
        }
    }
}
