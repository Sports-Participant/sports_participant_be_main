package com.example.sports_participant_be_main.utils;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
public class ExceptionResponse {
    private String message;
    private ZonedDateTime timestamp;
}
