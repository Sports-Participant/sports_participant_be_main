package com.example.sports_participant_be_main.controllers;

import com.example.sports_participant_be_main.dto.UserDto;
import com.example.sports_participant_be_main.security.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
@Slf4j
public class UserController {
    private final UserService userService;

    @GetMapping("/{user_id}")
    public ResponseEntity<UserDto> getById(@PathVariable("user_id") UUID userId) {
        return new ResponseEntity<>(
                this.userService.getById(userId).ofDto(),
                HttpStatus.OK
        );
    }
}
