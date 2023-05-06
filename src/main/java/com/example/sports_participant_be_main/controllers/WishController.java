package com.example.sports_participant_be_main.controllers;

import com.example.sports_participant_be_main.dto.WishDto;
import com.example.sports_participant_be_main.models.Wish;
import com.example.sports_participant_be_main.services.WishService;
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
public class WishController {

    private final WishService wishService;

    @PostMapping("/wishes")
    public ResponseEntity<WishDto> add(
            @Valid @RequestBody WishDto wishDto,
            @RequestParam String gymBrandName
    ) {
        return new ResponseEntity<>(
                this.wishService.save(
                        wishDto.ofEntity(),
                        gymBrandName
                ).ofDto(),
                HttpStatus.CREATED
        );
    }

//    @GetMapping
//    public ResponseEntity<Set<WishDto>> getAll(
//            @RequestParam ("status") Wish.Status status,
//            @RequestParam(defaultValue = "0") int page,
//            @RequestParam(defaultValue = "10") int size
//    ) {
//        return new ResponseEntity<>(
//                this.wishService.findAllByStatus(status, size, page)
//                        .stream().map(Wish::ofDto)
//                        .collect(Collectors.toSet()),
//                HttpStatus.OK
//        );
//    }

    @GetMapping("/gym_brands/{gym_brand_id}/wishes")
    public ResponseEntity<Set<WishDto>> getAll(
            @PathVariable("gym_brand_id") UUID gymBrandId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return new ResponseEntity<>(
                this.wishService.findAllByGymBrandId(gymBrandId, size, page)
                        .stream().map(Wish::ofDto)
                        .collect(Collectors.toSet()),
                HttpStatus.OK
        );
    }

    @GetMapping("/gym_brands/{gym_brand_id}/wishes/{wish_id}")
    public ResponseEntity<WishDto> getById(
            @PathVariable("gym_brand_id") UUID gymBrandId,
            @PathVariable ("wish_id") UUID id
    ) {
        return new ResponseEntity<>(
                this.wishService.findById(id).orElseThrow(() -> new RuntimeException("No wish")).ofDto(),
                HttpStatus.OK
        );
    }

    @PutMapping("/gym_brands/{gym_brand_id}/wishes/{wish_id}")
    public ResponseEntity<WishDto> update(
            @PathVariable("gym_brand_id") UUID gymBrandId,
            @PathVariable ("wish_id") UUID id,
            @Valid @RequestBody WishDto wishDto

    ) {
        return new ResponseEntity<>(
                this.wishService.update(
                        wishDto.ofEntity(),
                        gymBrandId
                ).ofDto(),
                HttpStatus.OK
        );
    }
}
