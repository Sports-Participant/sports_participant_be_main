package com.example.sports_participant_be_main.services;

import com.example.sports_participant_be_main.models.Location;
import com.example.sports_participant_be_main.models.Schedule;
import com.example.sports_participant_be_main.repositories.ScheduleRepo;
import com.example.sports_participant_be_main.utils.exceptions.location.LocationNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
@AllArgsConstructor
@Slf4j
public class ScheduleService {

    private final ScheduleRepo scheduleRepo;
    private final LocationService locationService;

    public Schedule save(Schedule schedule, UUID locationId) {
        Location location = this.locationService.findLocationById(locationId).orElseThrow(() -> {
            throw new LocationNotFoundException(locationId);
        });

        AtomicBoolean isExists = new AtomicBoolean(false);
        scheduleRepo.findByLocationIdAndDay(locationId, schedule.getDay())
                .ifPresent((item) -> {
                    log.warn("Schedule on day={} for location_id={}", schedule.getDay(), locationId);
                    item.setOpenTime(schedule.getOpenTime());
                    item.setCloseTime(schedule.getCloseTime());
                    item.setIsWeek(schedule.getIsWeek());
                    this.scheduleRepo.save(item);
                    isExists.set(true);
                });

        schedule.setLocation(location);
        if (isExists.get()) return schedule;
        schedule.setLocation(location);
        return this.scheduleRepo.save(schedule);
    }
}
