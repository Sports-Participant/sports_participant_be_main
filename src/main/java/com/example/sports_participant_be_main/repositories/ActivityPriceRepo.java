package com.example.sports_participant_be_main.repositories;

import com.example.sports_participant_be_main.models.ActivityPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ActivityPriceRepo extends JpaRepository<ActivityPrice, UUID> {
}
