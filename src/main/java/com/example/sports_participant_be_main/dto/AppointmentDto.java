package com.example.sports_participant_be_main.dto;

import com.example.sports_participant_be_main.models.Appointment;
import lombok.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentDto {

    private UUID id;

    @NotNull
    private String title;

    private String text;

    @NotNull
    private UUID location_id;

    @NotNull
    private LocalDateTime date_time;

    @NotNull
    private int duration_in_minutes;

    public Appointment ofEntity(){
        return Appointment.builder()
                .id(this.id)
                .title(this.title)
                .text(this.text)
                .dateTime(this.date_time)
                .durationInMinutes(this.duration_in_minutes)
                .build()
                ;
    }
}
