package com.example.sports_participant_be_main.security;

import com.example.sports_participant_be_main.dto.UserDto;
import com.example.sports_participant_be_main.models.Employee;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class User {

    private UUID id;
    private String email;
    private String password;
    private Set<RoleS> roles;
    private String firstname;
    private String lastname;
    private String phoneNumber;
    private Employee.Status status;
    private boolean isOwner;

    public User(UUID id, String email, String password, Set<RoleS> roles) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    public User(UUID id, String email, String password, Set<RoleS> roles, String firstname, String lastname, String phoneNumber, boolean isOwner) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.firstname = firstname;
        this.lastname = lastname;
        this.phoneNumber = phoneNumber;
        this.isOwner = isOwner;
    }

    public UserDto ofDto() {
        return UserDto.builder()
                .id(this.id)
                .firstname(this.firstname)
                .lastname(this.lastname)
                .email(this.email)
                .password(this.password)
                .status(this.status)
                .phoneNumber(this.phoneNumber)
                .build()
                ;
    }
}
