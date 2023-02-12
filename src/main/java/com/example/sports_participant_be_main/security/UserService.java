package com.example.sports_participant_be_main.security;

import com.example.sports_participant_be_main.services.EmployeeService;
import com.example.sports_participant_be_main.services.OwnerService;
import com.example.sports_participant_be_main.utils.exceptions.security.UserNotFoundException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final OwnerService ownerService;
    private final EmployeeService employeeService;

    public User getByEmail(@NonNull String email) {
        List<User> users = new ArrayList<>();
        ownerService.findOwnerByEmail(email).ifPresent(owner -> {
            users.add(new User(
                    owner.getEmail(),
                    owner.getPassword(),
                    Collections.singleton(owner.getRole())
                )
            );
        });
        employeeService.findEmployeeByEmail(email).ifPresent(employee -> {
            users.add(new User(
                    employee.getEmail(),
                    employee.getPassword(),
                    Collections.singleton(employee.getRole())
                )
            );
        });

        if (users.isEmpty())
            throw new UserNotFoundException();

        return users.get(0);
    }

}
