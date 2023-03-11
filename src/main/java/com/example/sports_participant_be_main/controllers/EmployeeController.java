package com.example.sports_participant_be_main.controllers;

import com.example.sports_participant_be_main.dto.EmployeeDto;
import com.example.sports_participant_be_main.models.Employee;
import com.example.sports_participant_be_main.services.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("/gym_brands/{gym_brand_id}/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<EmployeeDto> add(
            @PathVariable("gym_brand_id") UUID gymBrandId,
            @Valid @RequestBody EmployeeDto employeeDto
    ) {
        return new ResponseEntity<>(
                this.employeeService.save(employeeDto.ofEntity(), gymBrandId, employeeDto.getRoleIds()).ofDto(),
                HttpStatus.CREATED
        );
    }

    @GetMapping
    public ResponseEntity<Collection<EmployeeDto>> getAll(
            @PathVariable("gym_brand_id") UUID gymBrandId
    ) {
        return new ResponseEntity<>(
                this.employeeService.getAllByGymBrandId(gymBrandId)
                        .stream()
                        .map(Employee::ofDto)
                        .collect(Collectors.toSet()),
                HttpStatus.CREATED
        );
    }
}
