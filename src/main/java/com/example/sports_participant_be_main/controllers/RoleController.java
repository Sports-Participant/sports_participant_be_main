package com.example.sports_participant_be_main.controllers;

import com.example.sports_participant_be_main.dto.RoleDto;
import com.example.sports_participant_be_main.models.Role;
import com.example.sports_participant_be_main.security.UserService;
import com.example.sports_participant_be_main.services.RoleService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.management.relation.RoleNotFoundException;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users/{user_id}/roles")
@AllArgsConstructor
@Slf4j
public class RoleController {
    private final RoleService roleService;
    private final UserService userService;

    @GetMapping()
    public ResponseEntity<Set<RoleDto>> getByUserId(@PathVariable("user_id") UUID userId) throws RoleNotFoundException {
        Set<Role> roles = this.userService.getRolesByUserId(userId);

        return new ResponseEntity<>(
                roles.stream().map(Role::ofDto).collect(Collectors.toSet()),
                HttpStatus.OK
        );
    }
}
