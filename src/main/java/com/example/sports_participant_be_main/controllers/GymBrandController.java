package com.example.sports_participant_be_main.controllers;

import com.example.sports_participant_be_main.models.GymBrand;
import com.example.sports_participant_be_main.dto.GymBrandDto;
import com.example.sports_participant_be_main.security.User;
import com.example.sports_participant_be_main.security.UserService;
import com.example.sports_participant_be_main.services.EmployeeService;
import com.example.sports_participant_be_main.services.GymBrandService;
import com.example.sports_participant_be_main.services.OwnerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("/gym_brands")
public class GymBrandController {

    private final GymBrandService gymBrandService;
    private UserService userService;
    private final OwnerService ownerService;
    private final EmployeeService employeeService;

    @PostMapping("")
    public ResponseEntity<GymBrandDto> add(@Valid @RequestBody GymBrandDto gymBrandDto) {
        return new ResponseEntity<>(
                gymBrandService.save(gymBrandDto.ofEntity(), gymBrandDto.getOwnerId()).ofDto(),
                HttpStatus.CREATED
        );
    }

    @GetMapping
    public ResponseEntity<Collection<GymBrandDto>> getAllByUserId(@RequestParam("user_id") UUID userId) {
        User user = this.userService.getById(userId);
        Set<GymBrand> gymBrands;
        if (user.isOwner()) gymBrands = this.ownerService.getAllGymBrandsByOwnerId(userId);
        else gymBrands = Set.of(this.employeeService.findGymBrandByEmployeeId(userId));

        return new ResponseEntity<>(gymBrands
                .stream()
                .map(GymBrand::ofDto)
                .collect(Collectors.toList()),
                HttpStatus.OK
        );
    }
}
