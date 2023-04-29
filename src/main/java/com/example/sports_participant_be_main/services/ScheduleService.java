package com.example.sports_participant_be_main.services;

import com.example.sports_participant_be_main.models.Location;
import com.example.sports_participant_be_main.models.Schedule;
import com.example.sports_participant_be_main.repositories.ScheduleRepo;
import com.example.sports_participant_be_main.utils.exceptions.location.LocationNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
@AllArgsConstructor
@Slf4j
public class ScheduleService {

    private final ScheduleRepo scheduleRepo;
    private final LocationService locationService;
    private final AppointmentService appointmentService;

    public Schedule save(Schedule schedule, UUID locationId) {
        Location location = this.locationService.findLocationById(locationId).orElseThrow(() -> {
            throw new LocationNotFoundException(locationId);
        });

        AtomicBoolean isExists = new AtomicBoolean(false);
        scheduleRepo.findByLocationIdAndDay(locationId, schedule.getDay())
                .ifPresent((item) -> {
                    log.warn("Schedule on day={} for location_id={}", schedule.getDay(), locationId);
                    item.setOpenTime(this.appointmentService.mapTimeToMultipleOfNumber(schedule.getOpenTime(), 15));
                    item.setCloseTime(this.appointmentService.mapTimeToMultipleOfNumber(schedule.getCloseTime(), 15));
                    item.setIsWeekend(schedule.getIsWeekend());
                    this.scheduleRepo.save(item);
                    isExists.set(true);
                });

        if (isExists.get()) return schedule;
        schedule.setLocation(location);
        return this.scheduleRepo.save(schedule);
    }

    public Collection<Schedule> saveAll(Set<Schedule> schedules, UUID locationId) {
        Location location = this.locationService.findLocationById(locationId).orElseThrow(() -> {
            throw new LocationNotFoundException(locationId);
        });

        schedules.forEach(item -> {
            item.setOpenTime(this.appointmentService.mapTimeToMultipleOfNumber(item.getOpenTime(), 15));
            item.setCloseTime(this.appointmentService.mapTimeToMultipleOfNumber(item.getCloseTime(), 15));
            item.setLocation(location);
        });
        return this.scheduleRepo.saveAll(schedules);
    }
}
