package com.example.sports_participant_be_main.controllers;

import com.example.sports_participant_be_main.dto.ExerciseDto;
import com.example.sports_participant_be_main.models.Exercise;
import com.example.sports_participant_be_main.services.ExerciseService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/guides")
public class ExerciseController {

    private final ExerciseService exerciseService;

    @PostMapping
    public ResponseEntity<ExerciseDto> add(
            @Valid @RequestBody ExerciseDto exerciseDto
    ) {
        return new ResponseEntity<>(
                this.exerciseService.save(exerciseDto.ofEntity()).ofDto(),
                HttpStatus.CREATED
        );
    }

    @GetMapping
    public ResponseEntity<Set<ExerciseDto>> getAll(
            @RequestParam("disability") Exercise.Disability disability,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return new ResponseEntity<>(
                this.exerciseService.findAllByDisability(disability, size, page)
                        .stream().map(Exercise::ofDto)
                        .collect(Collectors.toSet()),
                HttpStatus.OK
        );
    }

    @GetMapping("/{guide_id}")
    public ResponseEntity<ExerciseDto> getById(
            @PathVariable ("guide_id") UUID id
    ) {
        return new ResponseEntity<>(
                this.exerciseService.findById(id).orElseThrow(() -> new RuntimeException("No exercise")).ofDto(),
                HttpStatus.OK
        );
    }
}
