package com.example.sports_participant_be_main.models;

import com.example.sports_participant_be_main.dto.AppointmentDto;
import com.example.sports_participant_be_main.utils.GlobalEntityProperties;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Entity
@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
@Table(name = "appointments")
public class Appointment extends GlobalEntityProperties {

    @Id
    @GeneratedValue(generator = "hibernate-uuid")
    @GenericGenerator(name = "hibernate-uuid", strategy = "uuid2")
    @Column(name = "id", columnDefinition = "BINARY(16)")
    @ToString.Include
    private UUID id;

    @Column(name = "title", nullable = false)
    @EqualsAndHashCode.Include
    @ToString.Include
    private String title;

    @Column(name = "text")
    @EqualsAndHashCode.Include
    @ToString.Include
    private String text;

    @ManyToOne
    @JoinColumn(name = "location_id", referencedColumnName = "id", nullable = false)
    private Location location;

    @Column(name = "date", nullable = false)
    @EqualsAndHashCode.Include
    @ToString.Include
    private LocalDate date;

    @Column(name = "start", nullable = false)
    @EqualsAndHashCode.Include
    @ToString.Include
    private LocalTime start;

    @Column(name = "end", nullable = false)
    @EqualsAndHashCode.Include
    @ToString.Include
    private LocalTime end;

    @Column(name = "status")
    @EqualsAndHashCode.Include
    @ToString.Include
    @Enumerated(EnumType.STRING)
    private Appointment.Status status;

    @ManyToOne
    @JoinColumn(name = "room_id", referencedColumnName = "id", nullable = false)
//    @OnDelete(action = OnDeleteAction.CASCADE)
    private LocationRoom room;

    @ManyToOne
    @JoinColumn(name = "activity_id", referencedColumnName = "id", nullable = true)
//    @OnDelete(action = OnDeleteAction.CASCADE)
    private Activity activity;

    @ManyToOne
    @JoinColumn(name = "employee_id", referencedColumnName = "id", nullable = false)
//    @OnDelete(action = OnDeleteAction.CASCADE)
    private Employee employee;

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
                .locationId(this.location.getId())
                .date(this.date)
                .start(this.start)
                .end(this.end)
                .roomId(this.room.getId())
                .employeeId(this.employee.getId())
                .status(this.status)
                .build()
                ;
    }

}
