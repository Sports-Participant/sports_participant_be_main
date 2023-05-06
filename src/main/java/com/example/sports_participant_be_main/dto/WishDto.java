package com.example.sports_participant_be_main.dto;

import com.example.sports_participant_be_main.models.Wish;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class WishDto {

    private UUID id;

    @NotNull
    private String firstname;

    @NotNull
    private String lastname;

    @NotNull
    @Email(regexp = ".+@.+\\..+", message = "Invalid email format")
    private String email;

    @NotNull
    private String phoneNumber;

    @NotNull
    private String text;

    private Wish.Status status;

    public Wish ofEntity(){
        return Wish.builder()
                .id(this.id)
                .firstname(this.firstname)
                .lastname(this.lastname)
                .email(this.email)
                .phoneNumber(this.phoneNumber)
                .text(this.text)
                .status(this.status)
                .build()
                ;
    }
}
