package com.example.sports_participant_be_main.services;

import com.example.sports_participant_be_main.models.Exercise;
import com.example.sports_participant_be_main.repositories.ExerciseRepo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class ExerciseService {

    private final ExerciseRepo exerciseRepo;

    public Exercise save(Exercise exercise) {
        return this.exerciseRepo.save(exercise);
    }

    public Optional<Exercise> findById(UUID id) {
        return this.exerciseRepo.findById(id);
    }

    public List<Exercise> findAllByDisability(Exercise.Disability disability, int limit, int page) {
        return this.exerciseRepo.findAllByDisability(disability, PageRequest.of(page, limit));
    }
}
