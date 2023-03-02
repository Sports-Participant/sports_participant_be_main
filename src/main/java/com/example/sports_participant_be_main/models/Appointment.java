package com.example.sports_participant_be_main.models;

import com.example.sports_participant_be_main.dto.AppointmentDto;
import com.example.sports_participant_be_main.utils.GlobalEntityProperties;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "appointments")
public class Appointment extends GlobalEntityProperties {

    @Id
    @GeneratedValue(generator = "hibernate-uuid")
    @GenericGenerator(name = "hibernate-uuid", strategy = "uuid2")
    @Column(name = "id", columnDefinition = "BINARY(16)")
    private UUID id;

    @Column(name = "title", nullable = false)
    @EqualsAndHashCode.Include
    private String title;

    @Column(name = "text")
    @EqualsAndHashCode.Include
    private String text;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "location_id", referencedColumnName = "id", nullable = false)
    private Location location;

    @Column(name = "date_time", nullable = false)
    @EqualsAndHashCode.Include
    private LocalDateTime dateTime;

    @Column(name = "duration_in_minutes", nullable = false)
    @EqualsAndHashCode.Include
    private int durationInMinutes;

    @Column(name = "status")
    @EqualsAndHashCode.Include
    @Enumerated(EnumType.STRING)
    private Appointment.Status status;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "room_id", referencedColumnName = "id")
    private LocationRoom room;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "activity_id", referencedColumnName = "id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Activity activity;

    @AllArgsConstructor
    public enum Status {
        ACTIVE("ACTIVE"),
        BANNED("BANNED"),
        DISABLED("DISABLED"),
        FROZEN("FROZEN"),
        ;

        private final String value;
    }

    // кімната, активність і то подібне

    public AppointmentDto ofDto() {
        return AppointmentDto.builder()
                .id(this.id)
                .title(this.title)
                .text(this.text)
                .location_id(this.location.getId())
                .date_time(this.dateTime)
                .duration_in_minutes(this.durationInMinutes)
                .room_id(this.room.getId())
                .status(this.status)
                .build()
                ;
    }

}
