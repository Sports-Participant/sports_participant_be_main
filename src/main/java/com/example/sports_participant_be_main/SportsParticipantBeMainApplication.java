package com.example.sports_participant_be_main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// todo Переписати логіку витягування медичної історії так, щоб можна було вліпити пагінацію
@SpringBootApplication
public class SportsParticipantBeMainApplication {

    public static void main(String[] args) {
        SpringApplication.run(SportsParticipantBeMainApplication.class, args);
    }

}
