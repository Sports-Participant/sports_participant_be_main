package com.example.sports_participant_be_main.models;

import com.example.sports_participant_be_main.dto.ScheduleDto;
import com.example.sports_participant_be_main.utils.GlobalEntityProperties;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Entity
@Table(name = "schedules")
@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
public class Schedule extends GlobalEntityProperties {

    @Id
    @GeneratedValue(generator = "hibernate-uuid")
    @GenericGenerator(name = "hibernate-uuid", strategy = "uuid2")
    @Column(name = "id", columnDefinition = "BINARY(16)")
    @EqualsAndHashCode.Include
    @ToString.Include
    private UUID id;

    @Column(name = "is_week", nullable = false)
    @EqualsAndHashCode.Include
    @ToString.Include
    private Boolean isWeekend = false;

    @Column(name = "day", nullable = false)
    @EqualsAndHashCode.Include
    @ToString.Include
    @Enumerated(EnumType.STRING)
    private Schedule.Day day;

    @Column(name = "open_time")
    @EqualsAndHashCode.Include
    @ToString.Include
    private LocalTime openTime;

    @Column(name = "close_time")
    @EqualsAndHashCode.Include
    @ToString.Include
    private LocalTime closeTime;

    @ManyToOne
    @JoinColumn(name = "location_id", referencedColumnName = "id", nullable = false, updatable = false)
//    @OnDelete(action = OnDeleteAction.CASCADE)
    private Location location;

    @AllArgsConstructor
    public enum Day {
        MONDAY("MONDAY"),
        TUESDAY("TUESDAY"),
        WEDNESDAY("WEDNESDAY"),
        THURSDAY("THURSDAY"),
        FRIDAY("FRIDAY"),
        SATURDAY("SATURDAY"),
        SUNDAY("SUNDAY"),
        ;

        private final String value;
    }

    public ScheduleDto ofDto() {
        return ScheduleDto.builder()
                .id(this.id)
                .isWeekend(this.isWeekend)
                .day(this.day)
                .openTime(Timestamp.valueOf(this.openTime.atDate(LocalDate.now())))
                .closeTime(Timestamp.valueOf(this.closeTime.atDate(LocalDate.now())))
                .locationId(this.location.getId())
                .build()
                ;
    }
}
