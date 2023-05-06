package com.example.sports_participant_be_main.services;

import com.example.sports_participant_be_main.models.GymBrand;
import com.example.sports_participant_be_main.models.Wish;
import com.example.sports_participant_be_main.repositories.WishRepo;
import com.example.sports_participant_be_main.utils.exceptions.gym_brand.GymBrandNotFoundException;
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
public class WishService {

    private final WishRepo wishRepo;
    private final GymBrandService gymBrandService;

    public Wish save(Wish wish, String gymBrandName) {
        GymBrand gymBrand = gymBrandService.findByName(gymBrandName).orElseThrow(() -> {
            throw new RuntimeException("No gym brand with name=" + gymBrandName);
        });

        wish.setStatus(Wish.Status.NOT_VIEWED);
        wish.setGymBrand(gymBrand);

        return this.wishRepo.save(wish);
    }

    public Wish update(Wish wish, UUID gymBrandId) {
        GymBrand gymBrand = this.gymBrandService.findById(gymBrandId).orElseThrow(() -> {
            throw new GymBrandNotFoundException(gymBrandId);
        });
        wish.setGymBrand(gymBrand);
        return this.wishRepo.save(wish);
    }

    public Optional<Wish> findById(UUID id) {
        return this.wishRepo.findById(id);
    }

    public List<Wish> findAllByStatus(Wish.Status status, int limit, int page) {
        return this.wishRepo.findAllByStatusOrderByCreatedAtDesc(status, PageRequest.of(page, limit));
    }

    public List<Wish> findAllByGymBrandId(UUID gymBrandId, int limit, int page) {
        GymBrand gymBrand = this.gymBrandService.findById(gymBrandId).orElseThrow(() -> {
            throw new GymBrandNotFoundException(gymBrandId);
        });

        return this.wishRepo.findAllByGymBrandIdOrderByCreatedAtDesc(gymBrand.getId(), PageRequest.of(page, limit));
    }
}
