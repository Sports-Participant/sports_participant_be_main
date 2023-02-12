package com.example.sports_participant_be_main.controllers;

import com.example.sports_participant_be_main.dto.EmployeeDto;
import com.example.sports_participant_be_main.models.Employee;
import com.example.sports_participant_be_main.services.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/staff/{staff_id}/gym_brands/{gym_brand_id}/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<EmployeeDto> add(
            @PathVariable("staff_id") UUID staffId,
            @PathVariable("gym_brand_id") UUID gymBrandId,
            @Valid @RequestBody EmployeeDto employeeDto
    ) {
        return new ResponseEntity<>(
                this.employeeService.save(employeeDto.ofEntity(), gymBrandId).ofDto(),
                HttpStatus.CREATED
        );
    }
}
