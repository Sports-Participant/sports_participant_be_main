package com.example.sports_participant_be_main.models;

import com.example.sports_participant_be_main.dto.LocationDto;
import com.example.sports_participant_be_main.utils.GlobalEntityProperties;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@Builder(toBuilder = true)
@Table(
        name = "locations",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"street", "street_number"})}
)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
public class Location extends GlobalEntityProperties {

    @Id
    @GeneratedValue(generator = "hibernate-uuid")
    @GenericGenerator(name = "hibernate-uuid", strategy = "uuid2")
    @Column(name = "id", columnDefinition = "BINARY(16)")
    @EqualsAndHashCode.Include
    private UUID id;

    @Column(name = "street", nullable = false)
    @EqualsAndHashCode.Include
    private String street;

    @Column(name = "street_number", nullable = false)
    @EqualsAndHashCode.Include
    private Integer streetNumber;

    @Column(name = "capacity", nullable = false)
    @EqualsAndHashCode.Include
    private Integer capacity;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "gym_brand_id", referencedColumnName = "id", nullable = false)
    private GymBrand gymBrand;

    @ManyToMany(mappedBy = "locations")
    private Set<Client> clients = new HashSet<>();

    @Column(name = "status")
    @EqualsAndHashCode.Include
    @Enumerated(EnumType.STRING)
    private Location.Status status;

    @OneToMany(mappedBy = "location")
    private Set<Appointment> appointments = new HashSet<>();

    @OneToMany(mappedBy = "location")
    private Set<LocationRoom> rooms = new HashSet<>();

    @OneToMany(mappedBy = "location")
    private Set<Schedule> schedules = new HashSet<>();

    @AllArgsConstructor
    public enum Status {
        ACTIVE("ACTIVE"),
        BANNED("BANNED"),
        DISABLED("DISABLED"),
        FROZEN("FROZEN"),
        ;

        private final String value;
    }

    public LocationDto ofDto() {
        return LocationDto.builder()
                .id(this.id)
                .street(this.street)
                .streetNumber(this.streetNumber)
                .capacity(this.capacity)
                .gym_brand_id(this.gymBrand.getId())
                .status(this.status)
                .build()
                ;
    }
}
