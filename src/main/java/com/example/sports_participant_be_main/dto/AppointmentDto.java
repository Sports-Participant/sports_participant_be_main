package com.example.sports_participant_be_main.dto;

import com.example.sports_participant_be_main.models.Appointment;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;
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
    private UUID locationId;

    @NotNull
    private UUID clientId;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate date;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
//    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime start;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
//    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime end;

    @NotNull
    private UUID roomId;

    @NotNull
    private UUID activityId;

    @NotNull
    private UUID employeeId;

    private Appointment.Status status;

    public Appointment ofEntity(){
        return Appointment.builder()
                .id(this.id)
                .title(this.title)
                .text(this.text)
                .date(this.date)
                .start(this.start)
                .end(this.end)
                .status(this.status)
                .build()
                ;
    }
}
