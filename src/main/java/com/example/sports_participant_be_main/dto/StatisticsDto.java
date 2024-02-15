package com.example.sports_participant_be_main.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.Map;
import java.util.UUID;

// Розбити в майбутньому на різні дані
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class StatisticsDto {

    private UUID gymBrandId;
    private UUID locationId;

    private int locationCountOfActivities;
    private int gymBrandCountOfActivities;

    private int locationCountOfAppointments;
    private int gymBrandCountOfAppointments;

    private int locationCountOfClients;
    private int gymBrandCountOfClients;

    private int locationCountOfEmployees;
    private int gymBrandCountOfEmployees;

    private int locationCountOfRooms;
    private int gymBrandCountOfRooms;

    private int locationCountOfClientsAddedMedicalCards;
    private int gymBrandCountOfClientsAddedMedicalCards;

    private int locationCountOfDisabledClients;
    private int gymBrandCountOfDisabledClients;

    // New (last 30 days)
    private int newLocationCountOfAppointments;
    private int newGymBrandCountOfAppointments;

    private int newLocationCountOfClients;
    private int newGymBrandCountOfClients;

    private int newLocationCountOfClientsAddedMedicalCards;
    private int newGymBrandCountOfClientsAddedMedicalCards;

    private int newLocationCountOfDisabledClients;
    private int newGymBrandCountOfDisabledClients;

    private Map<Integer, Integer> locationAverageAppointments;
    private Map<Integer, Integer> locationAverageClients;
    private Map<Integer, Integer> locationAverageClientsAddedMedicalCards;
    private Map<Integer, Integer> locationAverageDisabledClients;

    private Map<Integer, Integer> gymBrandAverageAppointments;
    private Map<Integer, Integer> gymBrandAverageClients;
    private Map<Integer, Integer> gymBrandAverageClientsAddedMedicalCards;
    private Map<Integer, Integer> gymBrandAverageDisabledClients;

    private Double locationAverageYearAppointments;
    private Double locationAverageYearClients;
    private Double locationAverageYearClientsAddedMedical;
    private Double locationAverageYearDisabledClients;

    private Double gymBrandAverageYearAppointments;
    private Double gymBrandAverageYearClients;
    private Double gymBrandAverageYearClientsAddedMedical;
    private Double gymBrandAverageYearDisabledClients;

    private int locationCountOfClientsVisitedMoreOneTime;
    private int gymBrandCountOfClientsVisitedMoreOneTime;

    private Map<String, Integer> typesAndCountOfDisability;

//    private Map<LocalDate, Integer> datesAndCountOfAppointments;
//    private Map<LocalDate, Integer> datesAndCountOfClients;
//    private Map<LocalDate, Integer> datesAndCountOfClientsAddedMedicalCards;
//    private Map<LocalDate, Integer> datesAndCountOfDisabledClients;
}
