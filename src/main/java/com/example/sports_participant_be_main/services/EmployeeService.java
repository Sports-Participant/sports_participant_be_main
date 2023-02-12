package com.example.sports_participant_be_main.services;

import com.example.sports_participant_be_main.models.Employee;
import com.example.sports_participant_be_main.models.GymBrand;
import com.example.sports_participant_be_main.repositories.EmployeeRepo;
import com.example.sports_participant_be_main.utils.ResponseMessages;
import com.example.sports_participant_be_main.utils.exceptions.gym_brand.GymBrandNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class EmployeeService {
    private final EmployeeRepo employeeRepo;
    private final GymBrandService gymBrandService;
    private final PasswordEncoder passwordEncoder;

    public Optional<Employee> findEmployeeByEmail(String email) {
        return employeeRepo.findEmployeeByEmail(email);
    }

    public Employee save(Employee employee, UUID gymBrandId) {
        GymBrand gymBrand = gymBrandService.findById(gymBrandId).orElseThrow(() -> {
            log.error(ResponseMessages.GymBrand.NOT_FOUND.message + " gymBrandId={}", gymBrandId);
            throw new GymBrandNotFoundException();
        });

        employee.setGymBrand(gymBrand);
        employee.setPassword(passwordEncoder.encode(employee.getPassword()));
        return this.employeeRepo.save(employee);
    }
}
