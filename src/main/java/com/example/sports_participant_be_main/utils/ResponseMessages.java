package com.example.sports_participant_be_main.utils;

public class ResponseMessages {

    public enum Owner {
        NOT_FOUND("Owner not found"),
        CREATED("Owner has been created"),
        OWNER_EXISTS("Owner already exists")
        ;

        public final String message;
        Owner(String message) {
            this.message = message;
        }
    }

    public enum Auth {
        INVALID_TOKEN("Invalid token"),
        INCORRECT_PASSWORD("Incorrect password"),
        EXPIRED_TOKEN("Token expired"),
        UNSUPPORTED_JWT("Unsupported jwt"),
        MALFORMED_JWT("Malformed jwt"),
        INVALID_SIGNATURE("Invalid signature")
        ;

        public final String message;
        Auth(String message) {
            this.message = message;
        }
    }

    public enum GymBrand {
        GYM_BRAND_EXISTS("Gym brand already exists")
        ;

        public final String message;
        GymBrand(String message) {
            this.message = message;
        }
    }

    public enum Other {
        INTERNAL_SERVER_ERROR("Internal server error")
        ;

        public final String message;
        Other(String message) {
            this.message = message;
        }
    }
}
