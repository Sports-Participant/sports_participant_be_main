package com.example.sports_participant_be_main.services;

import com.example.sports_participant_be_main.models.Activity;
import com.example.sports_participant_be_main.models.ActivityPrice;
import com.example.sports_participant_be_main.models.Employee;
import com.example.sports_participant_be_main.models.Location;
import com.example.sports_participant_be_main.repositories.ActivityPriceRepo;
import com.example.sports_participant_be_main.repositories.ActivityRepo;
import com.example.sports_participant_be_main.utils.exceptions.location.LocationNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class ActivityService {

    private final ActivityRepo activityRepo;
    private final ActivityPriceRepo activityPriceRepo;
    private final LocationService locationService;
    private final EmployeeService employeeService;

    public Activity save(Activity activity, ActivityPrice activityPrice, UUID locationId, Set<UUID> employeeIds) {
        Location location = locationService.findLocationById(locationId).orElseThrow(() -> {
            throw new LocationNotFoundException(locationId);
        });
        Set<Employee> employees = employeeService.getAllByIds(employeeIds);

        if (employees.size() != employeeIds.size())
            log.warn("The count of employee ids and the count of employees not equal.");

        activity.setLocation(location);
        activity.setEmployees(employees);
        Activity newActivity = activityRepo.save(activity);
        activityPrice.setActivity(newActivity);
        activityPriceRepo.save(activityPrice);

        return newActivity;
    }

    public Set<Activity> getAllByLocationId(UUID locationId){
        return activityRepo.findAllByLocationId(locationId);
    }

    // todo Mb needs to be deleted in future
    public Activity saveWithoutPrice(Activity activity, UUID locationId, Set<UUID> employeeIds) {
        Location location = locationService.findLocationById(locationId).orElseThrow(() -> {
            throw new LocationNotFoundException(locationId);
        });
//        Set<Employee> employees = employeeService.getAllByIds(employeeIds);
//
//        if (employees.size() != employeeIds.size())
//            log.warn("The count of employee ids and the count of employees not equal.");

        activity.setLocation(location);
//        activity.setEmployees(employees);

        return activityRepo.save(activity);
    }

    public Optional<Activity> findById(UUID id) {
        return this.activityRepo.findById(id);
    }
}
