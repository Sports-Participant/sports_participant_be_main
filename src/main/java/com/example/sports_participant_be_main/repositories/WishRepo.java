package com.example.sports_participant_be_main.repositories;

import com.example.sports_participant_be_main.models.Wish;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface WishRepo extends JpaRepository<Wish, UUID> {
    List<Wish> findAllByStatusOrderByCreatedAtDesc(Wish.Status status, Pageable pageable);
    List<Wish> findAllByGymBrandIdOrderByCreatedAtDesc(UUID gymBrandId, Pageable pageable);
}
