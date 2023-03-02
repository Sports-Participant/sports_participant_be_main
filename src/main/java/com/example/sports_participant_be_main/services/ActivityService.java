package com.example.sports_participant_be_main.services;

import com.example.sports_participant_be_main.models.Activity;
import com.example.sports_participant_be_main.models.Location;
import com.example.sports_participant_be_main.repositories.ActivityPriceRepo;
import com.example.sports_participant_be_main.repositories.ActivityRepo;
import com.example.sports_participant_be_main.utils.exceptions.location.LocationNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class ActivityService {

    private final ActivityRepo activityRepo;
    private final ActivityPriceRepo activityPriceRepo;
    private final LocationService locationService;

    public Activity save(Activity activity, UUID locationId) {
        Location location = locationService.findLocationById(locationId).orElseThrow(() -> {
            throw new LocationNotFoundException(locationId);
        });

        activity.setLocation(location);
        return activityRepo.save(activity);
    }
}
