package com.example.sports_participant_be_main.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalTime;

@AllArgsConstructor
@Data
public class HourDto {
    LocalTime time;
    Boolean isAvailable;
}
