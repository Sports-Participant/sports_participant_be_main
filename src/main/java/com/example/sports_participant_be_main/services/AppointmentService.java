package com.example.sports_participant_be_main.services;

import com.example.sports_participant_be_main.dto.HourDto;
import com.example.sports_participant_be_main.models.*;
import com.example.sports_participant_be_main.repositories.AppointmentRepo;
import com.example.sports_participant_be_main.utils.exceptions.location.LocationNotFoundException;
import com.example.sports_participant_be_main.utils.exceptions.location.location_room.LocationRoomNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class AppointmentService {

    private final LocationService locationService;
    private final AppointmentRepo appointmentRepo;
    private final EmployeeService employeeService;
    private final ActivityService activityService;

    public Appointment save(
            Appointment appointment,
            UUID locationId,
            UUID roomId,
            UUID employeeId,
            UUID activityId
    ) {
        Location location = this.locationService.findLocationById(locationId).orElseThrow(() -> {
            throw new LocationNotFoundException(locationId);
        });
        Employee employee = this.employeeService.findById(employeeId).orElseThrow(() -> {
            throw new RuntimeException("Employee not found");
        });
        LocationRoom room = this.locationService.findLocationRoomById(roomId).orElseThrow(() -> {
            throw new LocationRoomNotFoundException(roomId);
        });
        Activity activity = this.activityService.findById(activityId).orElseThrow(() -> {
            throw new RuntimeException("Activity not found");
        });

        appointment.setLocation(location);
        appointment.setRoom(room);
        appointment.setActivity(activity);
        appointment.setStatus(Appointment.Status.ACTIVE);
        appointment.setEmployee(employee);
        return this.appointmentRepo.save(appointment);
    }

    public List<HourDto> getListOfAvailableHours(LocalDate date, UUID coachId, UUID locationId) {
        Location location = this.locationService.findLocationById(locationId).orElseThrow(() -> {
            throw new LocationNotFoundException(locationId);
        });
        Set<Schedule> schedules = location.getSchedules();
        Schedule currentSchedule = schedules
                .stream()
                .filter(item -> date.getDayOfWeek().name().equals(item.getDay().name()))
                .findFirst()
                .orElseThrow(() -> {
                    throw new RuntimeException("No schedule for locationId" + locationId + " for day " + date.getDayOfWeek().name());
                });

        List<Appointment> appointments = this.getAllAppointmentsByDateAndEmployeeId(date, coachId);

        if (appointments.isEmpty())
            return new ArrayList<>(this.getTimeListWithStep(
                    15,
                    currentSchedule.getOpenTime(),
                    currentSchedule.getCloseTime()
            ))
                    .stream()
                    .map(item -> new HourDto(this.mapTimeToMultipleOfNumber(item, 15), true))
                    .collect(Collectors.toList());

        List<LocalTime> allHours = this.getTimeListWithStep(15,
                currentSchedule.getOpenTime(),
                currentSchedule.getCloseTime());

        List<HourDto> hoursDtos = new ArrayList<>();

        for (int hourPosition = 0, appointmentPosition = 0; hourPosition < allHours.size(); ++hourPosition) {
            if (this.mapTimeToMultipleOfNumber(allHours.get(hourPosition), 15).equals(appointments.get(appointmentPosition >= appointments.size() ? 0 : appointmentPosition).getStart())) {
                while (!this.mapTimeToMultipleOfNumber(allHours.get(hourPosition), 15).equals(appointments.get(appointmentPosition).getEnd())) {
                    hoursDtos.add(new HourDto(this.mapTimeToMultipleOfNumber(allHours.get(hourPosition), 15), false));
                    ++hourPosition;
                }
                hoursDtos.add(new HourDto(this.mapTimeToMultipleOfNumber(allHours.get(hourPosition), 15), true));
                ++appointmentPosition;
            }
            else {
                hoursDtos.add(new HourDto(this.mapTimeToMultipleOfNumber(allHours.get(hourPosition), 15), true));
            }
        }
        return hoursDtos;
    }

    public List<LocalTime> getTimeListWithStep(int step, LocalTime start, LocalTime end) {
        List<LocalTime> timeList = new ArrayList<>();

        LocalTime current = start;
        timeList.add(current);
        while (!current.equals(end)) {
            current = current.plusMinutes(step);
            timeList.add(current);
        }

        return timeList;
    }

    public LocalTime mapTimeToMultipleOfNumber(LocalTime time, double number) {
        int roundedMinutes = (int) (Math.round(time.getMinute() / number) * number);
        int hours = roundedMinutes != 60
                ? time.getHour()
                : time.getHour() + 1 >= 24
                ? 0
                : time.getHour() + 1;
        int minutes = roundedMinutes == 60 ? 0 : roundedMinutes;
        return LocalTime.of(hours, minutes, 0);
    }

    public List<Appointment> getAllAppointmentsByDateAndEmployeeId(LocalDate date, UUID employeeId) {
        Employee employee = this.employeeService.findById(employeeId).orElseThrow(() -> {
            throw new RuntimeException("Employee not found");
        });
//        if (employee.getRoles().stream()
//                .map(Role::getName)
//                .filter(item -> Objects.equals(item, com.example.sports_participant_be_main.constants.Role.COACH.message))
//                .count() == 0)
//        {
//            throw new RuntimeException("Employee does not have COACH role");
//        }


        return this.appointmentRepo.getAllByDateAndEmployeeIdOrderByStart(date, employeeId);
    }

    public List<Appointment> getAllByDate(LocalDate date) {
        return this.appointmentRepo.getAllByDateOrderByStart(date);
    }

    public void delete(UUID id) {
        this.appointmentRepo.deleteById(id);
    }
}
