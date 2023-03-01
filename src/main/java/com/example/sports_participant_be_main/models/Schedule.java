package com.example.sports_participant_be_main.models;

import com.example.sports_participant_be_main.dto.ScheduleDto;
import com.example.sports_participant_be_main.utils.GlobalEntityProperties;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.UUID;

@Entity
@Table(name = "schedules")
@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Schedule extends GlobalEntityProperties {

    @Id
    @GeneratedValue(generator = "hibernate-uuid")
    @GenericGenerator(name = "hibernate-uuid", strategy = "uuid2")
    @Column(name = "id", columnDefinition = "BINARY(16)")
    @EqualsAndHashCode.Include
    private UUID id;

    @Column(name = "is_week", nullable = false)
    @EqualsAndHashCode.Include
    private Boolean isWeek = false;

    @Column(name = "day", nullable = false)
    @EqualsAndHashCode.Include
    @Enumerated(EnumType.STRING)
    private Schedule.Day day;

    @Column(name = "open_time", nullable = false)
    @EqualsAndHashCode.Include
    private LocalTime openTime;

    @Column(name = "close_time", nullable = false)
    @EqualsAndHashCode.Include
    private LocalTime closeTime;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "location_id", referencedColumnName = "id", nullable = false, updatable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
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
                .is_week(this.isWeek)
                .day(this.day)
                .open_time(this.openTime)
                .close_time(this.closeTime)
                .location_id(this.location.getId())
                .build()
                ;
    }
}
