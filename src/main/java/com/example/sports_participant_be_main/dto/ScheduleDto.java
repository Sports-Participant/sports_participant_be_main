package com.example.sports_participant_be_main.dto;

import com.example.sports_participant_be_main.models.Schedule;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleDto {

    private UUID id;

    @NotNull
    private Boolean isWeekend;

    @NotNull
    private Schedule.Day day;

    private Timestamp openTime;

    private Timestamp closeTime;

    @NotNull
    private UUID locationId;

    public Schedule ofEntity() {
        return Schedule.builder()
                .id(this.id)
                .isWeekend(this.isWeekend)
                .day(this.day)
                .openTime(LocalTime
                        .parse(this.openTime
                                .toLocalDateTime()
                                .toLocalTime()
                                .format(DateTimeFormatter.ofPattern("HH:mm"))
                        )
                )
                .closeTime(LocalTime
                        .parse(this.closeTime
                                .toLocalDateTime()
                                .toLocalTime()
                                .format(DateTimeFormatter.ofPattern("HH:mm"))
                        )
                )
                .build()
                ;
    }
}
