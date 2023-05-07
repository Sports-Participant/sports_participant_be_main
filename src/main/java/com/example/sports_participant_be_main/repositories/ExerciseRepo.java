package com.example.sports_participant_be_main.repositories;

import com.example.sports_participant_be_main.models.Exercise;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ExerciseRepo extends JpaRepository<Exercise, UUID> {
    List<Exercise> findAllByDisability(Exercise.Disability disability, Pageable pageable);
}
