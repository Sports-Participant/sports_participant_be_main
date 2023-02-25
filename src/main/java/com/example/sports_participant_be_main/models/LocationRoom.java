package com.example.sports_participant_be_main.models;

import com.example.sports_participant_be_main.dto.LocationRoomDto;
import com.example.sports_participant_be_main.utils.GlobalEntityProperties;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@Builder(toBuilder = true)
@Table(name = "location_rooms")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
public class LocationRoom extends GlobalEntityProperties {

    @Id
    @GeneratedValue(generator = "hibernate-uuid")
    @GenericGenerator(name = "hibernate-uuid", strategy = "uuid2")
    @Column(name = "id", columnDefinition = "BINARY(16)")
    @EqualsAndHashCode.Include
    private UUID id;

    @Column(name = "name", nullable = false)
    @EqualsAndHashCode.Include
    private String name;

    @Column(name = "description")
    @EqualsAndHashCode.Include
    private String description;

    @Column(name = "room_number")
    @EqualsAndHashCode.Include
    private Integer roomNumber;

    @Column(name = "capacity")
    @EqualsAndHashCode.Include
    private Integer capacity;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "location_id", referencedColumnName = "id", nullable = false)
    private Location location;

    @Column(name = "status")
    @EqualsAndHashCode.Include
    @Enumerated(EnumType.STRING)
    private Location.Status status;

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
                .room_number(this.roomNumber)
                .capacity(capacity)
                .location_id(this.location.getId())
                .status(this.status)
                .build()
                ;
    }
}
