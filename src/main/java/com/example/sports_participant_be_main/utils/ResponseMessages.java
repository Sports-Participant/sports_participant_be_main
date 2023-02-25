package com.example.sports_participant_be_main.utils;

public class ResponseMessages {

    public enum Owner {
        NOT_FOUND("Owner not found"),
        CREATED("Owner has been created"),
        ALREADY_EXISTS("Owner already exists")
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
        ALREADY_EXISTS("Gym brand already exists"),
        NOT_FOUND("Gym brand not found"),
        ;

        public final String message;
        GymBrand(String message) { this.message = message; }
    }

    public enum Other {
        INTERNAL_SERVER_ERROR("Internal server error")
        ;

        public final String message;
        Other(String message) {
            this.message = message;
        }
    }

    public enum Security {
        USER_NOT_FOUND("User not found")
        ;

        public final String message;
        Security(String message) {
            this.message = message;
        }
    }

    public enum Location {
        ALREADY_EXISTS("Location already exists"),
        NOT_FOUND("Location not found"),
        ANY_LOCATION_NOT_FOUND("None of the locations were found")
        ;

        public final String message;
        Location(String message) {
            this.message = message;
        }
    }

    public enum LocationRoom {
        NOT_FOUND("Location room not found"),
        ;

        public final String message;
        LocationRoom(String message) {
            this.message = message;
        }
    }
}
