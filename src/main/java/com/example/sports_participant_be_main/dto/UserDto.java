package com.example.sports_participant_be_main.dto;

import com.example.sports_participant_be_main.models.Employee;
import com.example.sports_participant_be_main.security.User;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private UUID id;

    @NonNull
    private String firstname;

    @NotNull
    private String lastname;

    @NotNull
    @Email(regexp = ".+@.+\\..+", message = "Invalid email format")
    private String email;

    @NotNull
    private String password;

    @NotNull
    private String phoneNumber;

    private Employee.Status status;

    public User ofEntity(){
        return User.builder()
                .id(this.id)
                .firstname(this.firstname)
                .lastname(this.lastname)
                .email(this.email)
                .password(this.password)
                .phoneNumber(this.phoneNumber)
                .status(this.status)
                .build()
                ;
    }
}
