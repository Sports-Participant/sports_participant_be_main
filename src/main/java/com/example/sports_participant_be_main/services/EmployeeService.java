package com.example.sports_participant_be_main.services;

import com.example.sports_participant_be_main.models.Employee;
import com.example.sports_participant_be_main.models.GymBrand;
import com.example.sports_participant_be_main.models.Role;
import com.example.sports_participant_be_main.repositories.EmployeeRepo;
import com.example.sports_participant_be_main.utils.exceptions.gym_brand.GymBrandNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class EmployeeService {
    private final EmployeeRepo employeeRepo;
    private final GymBrandService gymBrandService;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;

    public Optional<Employee> findEmployeeByEmail(String email) {
        return employeeRepo.findEmployeeByEmail(email);
    }

    public Employee save(Employee employee, UUID gymBrandId, Set<UUID> roleIds) {
        GymBrand gymBrand = gymBrandService.findById(gymBrandId).orElseThrow(() -> {throw new GymBrandNotFoundException(gymBrandId);});
        Set<Role> roles = roleService.getRolesByIdIn(roleIds);

        if (roles.size() != roleIds.size())
            log.warn("The count of role ids and the count of roles not equal.");

        employee.setRoles(roles);
        employee.setGymBrand(gymBrand);
        employee.setPassword(passwordEncoder.encode(employee.getPassword()));
        return this.employeeRepo.save(employee);
    }

    public Optional<Employee> findById(UUID id) {
        return this.employeeRepo.findById(id);
    }

    public GymBrand findGymBrandByEmployeeId(UUID id) {
        Employee employee = this.findById(id).orElseThrow(() -> {throw new RuntimeException("Not found employee");});
        return employee.getGymBrand();
    }
}
