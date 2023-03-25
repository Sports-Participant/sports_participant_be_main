package com.example.sports_participant_be_main.models;

import com.example.sports_participant_be_main.dto.LocationRoomDto;
import com.example.sports_participant_be_main.utils.GlobalEntityProperties;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@Builder(toBuilder = true)
@Table(name = "location_rooms")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
@AllArgsConstructor
public class LocationRoom extends GlobalEntityProperties {

    @Id
    @GeneratedValue(generator = "hibernate-uuid")
    @GenericGenerator(name = "hibernate-uuid", strategy = "uuid2")
    @Column(name = "id", columnDefinition = "BINARY(16)")
    @EqualsAndHashCode.Include
    @ToString.Include
    private UUID id;

    @Column(name = "name", nullable = false)
    @EqualsAndHashCode.Include
    @ToString.Include
    private String name;

    @Column(name = "description")
    @EqualsAndHashCode.Include
    @ToString.Include
    private String description;

    @Column(name = "room_number")
    @EqualsAndHashCode.Include
    @ToString.Include
    private Integer roomNumber;

    @Column(name = "capacity")
    @EqualsAndHashCode.Include
    @ToString.Include
    private Integer capacity;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "location_id", referencedColumnName = "id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Location location;

    @Column(name = "status")
    @EqualsAndHashCode.Include
    @ToString.Include
    @Enumerated(EnumType.STRING)
    private LocationRoom.Status status;

    @OneToMany(mappedBy = "room")
    private Set<Appointment> appointments = new HashSet<>();

    @AllArgsConstructor
    public enum Status {
        ACTIVE("ACTIVE"),
        CLOSED("BANNED"),
        UNDER_REPAIR("UNDER_REPAIR"),
        ;

        private final String value;
    }

    public LocationRoomDto ofDto() {
        return LocationRoomDto.builder()
                .id(this.id)
                .name(this.name)
                .description(this.description)
                .roomNumber(this.roomNumber)
                .capacity(capacity)
                .locationId(this.location.getId())
                .status(this.status)
                .build()
                ;
    }
}
