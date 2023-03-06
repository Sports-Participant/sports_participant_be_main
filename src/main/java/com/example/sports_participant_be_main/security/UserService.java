package com.example.sports_participant_be_main.security;

import com.example.sports_participant_be_main.models.Role;
import com.example.sports_participant_be_main.services.EmployeeService;
import com.example.sports_participant_be_main.services.OwnerService;
import com.example.sports_participant_be_main.utils.exceptions.security.UserNotFoundException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.management.relation.RoleNotFoundException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class UserService {

    private final OwnerService ownerService;
    private final EmployeeService employeeService;

    public User getByEmail(@NonNull String email) {
        List<User> users = new ArrayList<>();
        ownerService.findOwnerByEmail(email).ifPresent(owner -> {
            users.add(new User(
                            owner.getId(),
                            owner.getEmail(),
                            owner.getPassword(),
                            Stream.of(owner.getRole()).map(item -> new RoleS(item.getName())).collect(Collectors.toSet())
                    )
            );
        });
        employeeService.findEmployeeByEmail(email).ifPresent(employee -> {
            users.add(new User(
                            employee.getId(),
                            employee.getEmail(),
                            employee.getPassword(),
                            employee.getRoles().stream().map(item -> new RoleS(item.getName())).collect(Collectors.toSet())
                    )
            );
        });

        if (users.isEmpty())
            throw new UserNotFoundException();

        return users.get(0);
    }

    public User getById(@NonNull UUID userId) {
        List<User> users = new ArrayList<>();
        this.ownerService.findById(userId).ifPresent(owner -> {
            users.add(new User(
                            owner.getId(),
                            owner.getEmail(),
                            owner.getPassword(),
                            Stream.of(owner.getRole()).map(item -> new RoleS(item.getName())).collect(Collectors.toSet()),
                            owner.getFirstname(),
                            owner.getLastname(),
                            owner.getPhoneNumber(),
                    true
                    )
            );
        });
        this.employeeService.findById(userId).ifPresent(employee -> {
            users.add(new User(
                            employee.getId(),
                            employee.getEmail(),
                            employee.getPassword(),
                            employee.getRoles().stream().map(item -> new RoleS(item.getName())).collect(Collectors.toSet()),
                            employee.getFirstname(),
                            employee.getLastname(),
                            employee.getPhoneNumber(),
                            employee.getStatus(),
                            false
                    )
            );
        });

        if (users.isEmpty())
            throw new UserNotFoundException();

        return users.get(0);
    }

    public Set<Role> getRolesByUserId(UUID userId) throws RoleNotFoundException {
        Set<Role> roles = new HashSet<>();
        this.ownerService.findById(userId).ifPresent(owner -> roles.add(owner.getRole()));
        this.employeeService.findById(userId).ifPresent(employee -> roles.addAll(employee.getRoles()));

        if (roles.isEmpty())
            throw new RoleNotFoundException();

        return roles;
    }

}
