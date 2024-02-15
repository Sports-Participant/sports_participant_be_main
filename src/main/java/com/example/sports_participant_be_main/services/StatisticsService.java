package com.example.sports_participant_be_main.services;

import com.example.sports_participant_be_main.dto.StatisticsDto;
import com.example.sports_participant_be_main.models.Appointment;
import com.example.sports_participant_be_main.models.Client;
import com.example.sports_participant_be_main.models.Location;
import com.example.sports_participant_be_main.models.MedicalCard;
import com.example.sports_participant_be_main.repositories.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class StatisticsService {

    private final GymBrandRepo gymBrandRepo;
    private final LocationRepo locationRepo;
    private final LocationRoomRepo locationRoomRepo;
    private final ActivityRepo activityRepo;
    private final AppointmentRepo appointmentRepo;
    private final ClientRepo clientRepo;
    private final EmployeeRepo employeeRepo;
    private final MedicalCardRepo medicalCardRepo;

    public StatisticsDto getStatistics(UUID gymBrandId, UUID locationId) {
        int locationCountOfActivities = this.activityRepo.countActivitiesByLocationId(locationId);
        int gymBrandCountOfActivities = this.activityRepo.countActivitiesByGymBrandId(gymBrandId);

        int locationCountOfAppointments = this.appointmentRepo.countAppointmentsByLocationId(locationId);
        int gymBrandCountOfAppointments = this.appointmentRepo.countAppointmentsByGymBrandId(gymBrandId);

        int locationCountOfClients = this.clientRepo.countClientsByLocationsIdIn(List.of(locationId));
        int gymBrandCountOfClients = this.clientRepo.countClientsByGymBrandId(gymBrandId);

        int locationCountOfEmployees = this.employeeRepo.countEmployeesByLocationId(locationId);
        int gymBrandCountOfEmployees = this.employeeRepo.countEmployeesByGymBrandId(gymBrandId);

        int locationCountOfRooms = this.locationRoomRepo.countLocationRoomsByLocationId(locationId);
        int gymBrandCountOfRooms = this.locationRoomRepo.countLocationRoomsByGymBrandId(gymBrandId);

        Collection<UUID> locations = this.locationRepo.getAllByGymBrandId(gymBrandId).stream().map(Location::getId).collect(Collectors.toSet());
        Collection<Client> clientsByLocationId = this.clientRepo.getClientsByLocationsIdIn(List.of(locationId));
        Collection<Client> clientsByGymBrandId = this.clientRepo.getClientsByLocationsIdIn(locations);

        int locationCountOfClientsAddedMedicalCards = this.medicalCardRepo.countMedicalCardsByClientIdIn(clientsByLocationId.stream().map(Client::getId).collect(Collectors.toSet()));
        int gymBrandCountOfClientsAddedMedicalCards = this.medicalCardRepo.countMedicalCardsByClientIdIn(clientsByGymBrandId.stream().map(Client::getId).collect(Collectors.toSet()));

        int locationCountOfDisabledClients = clientsByLocationId.stream().filter(Client::getIsDisabled).collect(Collectors.toSet()).size();
        int gymBrandCountOfDisabledClients = clientsByGymBrandId.stream().filter(Client::getIsDisabled).collect(Collectors.toSet()).size();

        int newLocationCountOfAppointments = this.appointmentRepo.countAppointmentsByCreatedAtAfterAndLocationId(LocalDateTime.now().minusDays(30), locationId);
        int newGymBrandCountOfAppointments = this.appointmentRepo.countAppointmentsByCreatedAtAfterAndGymBrandId(LocalDateTime.now().minusDays(30), gymBrandId);

        int newLocationCountOfClients = this.clientRepo.countClientsByCreatedAtAfterAndLocationsIdIn(LocalDateTime.now().minusDays(30), List.of(locationId));
        int newGymBrandCountOfClients = this.clientRepo.countClientsByCreatedAtAfterAndGymBrandId(LocalDateTime.now().minusDays(30), gymBrandId);

        int newLocationCountOfClientsAddedMedicalCards = this.medicalCardRepo.countMedicalCardsByClientIdInAndCreatedAtAfter(clientsByLocationId.stream().map(Client::getId).collect(Collectors.toSet()), LocalDateTime.now().minusDays(30));
        int newGymBrandCountOfClientsAddedMedicalCards = this.medicalCardRepo.countMedicalCardsByClientIdInAndCreatedAtAfter(clientsByGymBrandId.stream().map(Client::getId).collect(Collectors.toSet()), LocalDateTime.now().minusDays(30));

        int newLocationCountOfDisabledClients = clientsByLocationId.stream().filter(x -> {
            return x.getIsDisabled() && x.getCreatedAt().getDayOfYear() - LocalDateTime.now().minusDays(30).getDayOfYear() <= 30;
        }).collect(Collectors.toSet()).size();
        int newGymBrandCountOfDisabledClients = clientsByGymBrandId.stream().filter(x -> {
            return x.getIsDisabled() && x.getCreatedAt().getDayOfYear() - LocalDateTime.now().minusDays(30).getDayOfYear() <= 30;
        }).collect(Collectors.toSet()).size();

        List<Appointment> appointmentsByLocationId = this.appointmentRepo.getAppointmentsByLocationId(locationId);
        List<Appointment> appointmentsByGymBrandId = this.appointmentRepo.getAppointmentsByGymBrandId(gymBrandId);

        List<MedicalCard> medicalCardsByLocationId = this.medicalCardRepo.findAllByClientIdIn(clientsByLocationId.stream().map(Client::getId).collect(Collectors.toList()));
        List<MedicalCard> medicalCardsByGymBrandId = this.medicalCardRepo.findAllByClientIdIn(clientsByLocationId.stream().map(Client::getId).collect(Collectors.toList()));


        Map<Integer, Integer> locationAverageAppointments = this.getYearCountOfAppointmentsByMonth(appointmentsByLocationId);
        Map<Integer, Integer> locationAverageClients = this.getYearCountOfClientsByMonth((List<Client>) clientsByLocationId);
        Map<Integer, Integer> locationAverageClientsAddedMedicalCards = this.getYearCountOfMedicalCardsByMonth(medicalCardsByLocationId);
        Map<Integer, Integer> locationAverageDisabledClients = this.getYearCountOfClientsByMonth(clientsByLocationId.stream().filter(Client::getIsDisabled).collect(Collectors.toList()));

        Map<Integer, Integer> gymBrandAverageAppointments = this.getYearCountOfAppointmentsByMonth(appointmentsByGymBrandId);
        Map<Integer, Integer> gymBrandAverageClients = this.getYearCountOfClientsByMonth((List<Client>) clientsByGymBrandId);
        Map<Integer, Integer> gymBrandAverageClientsAddedMedicalCards = this.getYearCountOfMedicalCardsByMonth(medicalCardsByGymBrandId);
        Map<Integer, Integer> gymBrandAverageDisabledClients = this.getYearCountOfClientsByMonth(clientsByGymBrandId.stream().filter(Client::getIsDisabled).collect(Collectors.toList()));

        Double locationAverageYearAppointments = 0.0;
        Double locationAverageYearClients = 0.0;
        Double locationAverageYearClientsAddedMedical = 0.0;
        Double locationAverageYearDisabledClients = 0.0;

        Double gymBrandAverageYearAppointments = 0.0;
        Double gymBrandAverageYearClients = 0.0;
        Double gymBrandAverageYearClientsAddedMedical = 0.0;
        Double gymBrandAverageYearDisabledClients = 0.0;

        for (Map.Entry<Integer, Integer> entry : locationAverageAppointments.entrySet()) locationAverageYearAppointments += entry.getValue();
        for (Map.Entry<Integer, Integer> entry : locationAverageClients.entrySet()) locationAverageYearClients += entry.getValue();
        for (Map.Entry<Integer, Integer> entry : locationAverageClientsAddedMedicalCards.entrySet()) locationAverageYearClientsAddedMedical += entry.getValue();
        for (Map.Entry<Integer, Integer> entry : locationAverageDisabledClients.entrySet()) locationAverageYearDisabledClients += entry.getValue();
        for (Map.Entry<Integer, Integer> entry : gymBrandAverageAppointments.entrySet()) gymBrandAverageYearAppointments += entry.getValue();
        for (Map.Entry<Integer, Integer> entry : gymBrandAverageClients.entrySet()) gymBrandAverageYearClients += entry.getValue();
        for (Map.Entry<Integer, Integer> entry : gymBrandAverageClientsAddedMedicalCards.entrySet()) gymBrandAverageYearClientsAddedMedical += entry.getValue();
        for (Map.Entry<Integer, Integer> entry : gymBrandAverageDisabledClients.entrySet()) gymBrandAverageYearDisabledClients += entry.getValue();

        locationAverageYearAppointments  /= 12;
        locationAverageYearClients /= 12;
        locationAverageYearClientsAddedMedical /= 12;
        locationAverageYearDisabledClients /= 12;

        gymBrandAverageYearAppointments  /= 12;
        gymBrandAverageYearClients /= 12;
        gymBrandAverageYearClientsAddedMedical /= 12;
        gymBrandAverageYearDisabledClients /= 12;

        int locationCountOfClientsVisitedMoreOneTime = 0;
        int gymBrandCountOfClientsVisitedMoreOneTime = 0;

        for (Client c: clientsByLocationId) {
            if (c.getAppointments().size() > 1) locationCountOfClientsVisitedMoreOneTime += 1;
        }

        for (Client c: clientsByGymBrandId) {
            if (c.getAppointments().size() > 1) gymBrandCountOfClientsVisitedMoreOneTime += 1;
        }

        return StatisticsDto.builder()
                .gymBrandId(gymBrandId)
                .locationId(locationId)
                .locationCountOfActivities(locationCountOfActivities)
                .gymBrandCountOfActivities(gymBrandCountOfActivities)
                .locationCountOfAppointments(locationCountOfAppointments)
                .gymBrandCountOfAppointments(gymBrandCountOfAppointments)
                .locationCountOfClients(locationCountOfClients)
                .gymBrandCountOfClients(gymBrandCountOfClients)
                .locationCountOfEmployees(locationCountOfEmployees)
                .gymBrandCountOfEmployees(gymBrandCountOfEmployees)
                .locationCountOfRooms(locationCountOfRooms)
                .gymBrandCountOfRooms(gymBrandCountOfRooms)
                .locationCountOfClientsAddedMedicalCards(locationCountOfClientsAddedMedicalCards)
                .gymBrandCountOfClientsAddedMedicalCards(gymBrandCountOfClientsAddedMedicalCards)
                .locationCountOfDisabledClients(locationCountOfDisabledClients)
                .gymBrandCountOfDisabledClients(gymBrandCountOfDisabledClients)

                .newLocationCountOfAppointments(newLocationCountOfAppointments)
                .newGymBrandCountOfAppointments(newGymBrandCountOfAppointments)
                .newLocationCountOfClients(newLocationCountOfClients)
                .newGymBrandCountOfClients(newGymBrandCountOfClients)
                .newLocationCountOfClientsAddedMedicalCards(newLocationCountOfClientsAddedMedicalCards)
                .newGymBrandCountOfClientsAddedMedicalCards(newGymBrandCountOfClientsAddedMedicalCards)
                .newLocationCountOfDisabledClients(newLocationCountOfDisabledClients)
                .newGymBrandCountOfDisabledClients(newGymBrandCountOfDisabledClients)

                .locationAverageAppointments(locationAverageAppointments)
                .locationAverageClients(locationAverageClients)
                .locationAverageClientsAddedMedicalCards(locationAverageClientsAddedMedicalCards)
                .locationAverageDisabledClients(locationAverageDisabledClients)
                .gymBrandAverageAppointments(gymBrandAverageAppointments)
                .gymBrandAverageClients(gymBrandAverageClients)
                .gymBrandAverageClientsAddedMedicalCards(gymBrandAverageClientsAddedMedicalCards)
                .gymBrandAverageDisabledClients(gymBrandAverageDisabledClients)

                .locationAverageYearAppointments(locationAverageYearAppointments)
                .locationAverageYearClients(locationAverageYearClients)
                .locationAverageYearClientsAddedMedical(locationAverageYearClientsAddedMedical)
                .locationAverageYearDisabledClients(locationAverageYearDisabledClients)
                .gymBrandAverageYearAppointments(gymBrandAverageYearAppointments)
                .gymBrandAverageYearClients(gymBrandAverageYearClients)
                .gymBrandAverageYearClientsAddedMedical(gymBrandAverageYearClientsAddedMedical)
                .gymBrandAverageYearDisabledClients(gymBrandAverageYearDisabledClients)

                .locationCountOfClientsVisitedMoreOneTime(locationCountOfClientsVisitedMoreOneTime)
                .gymBrandCountOfClientsVisitedMoreOneTime(gymBrandCountOfClientsVisitedMoreOneTime)

                .build();
    }

    private Map<Integer, Integer> getYearCountOfAppointmentsByMonth(List<Appointment> arr) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 1; i <= 12; i++) map.put(i, 0);

        for (Appointment appointment : arr) {
            int month = appointment.getCreatedAt().getMonthValue();
            map.put(month, map.get(month) + 1);
        }

        return map;
    }

    private Map<Integer, Integer> getYearCountOfClientsByMonth(List<Client> arr) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 1; i <= 12; i++) map.put(i, 0);

        for (Client client : arr) {
            int month = client.getCreatedAt().getMonthValue();
            map.put(month, map.get(month) + 1);
        }

        return map;
    }

    private Map<Integer, Integer> getYearCountOfMedicalCardsByMonth(List<MedicalCard> arr) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 1; i <= 12; i++) map.put(i, 0);

        for (MedicalCard medicalCard : arr) {
            int month = medicalCard.getCreatedAt().getMonthValue();
            map.put(month, map.get(month) + 1);
        }

        return map;
    }
}
