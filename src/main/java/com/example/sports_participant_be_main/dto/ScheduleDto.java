package com.example.sports_participant_be_main.dto;

import com.example.sports_participant_be_main.models.Schedule;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.time.LocalTime;
import java.util.UUID;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleDto {

    private UUID id;

    @NotNull
    private Boolean is_week;

    @NotNull
    private Schedule.Day day;

    @NotNull
    private LocalTime open_time;

    @NotNull
    private LocalTime close_time;

    @NotNull
    private UUID location_id;

    public Schedule ofEntity(){
        return Schedule.builder()
                .id(this.id)
                .isWeek(this.is_week)
                .day(this.day)
                .openTime(this.open_time)
                .closeTime(this.close_time)
                .build()
                ;
    }
}
