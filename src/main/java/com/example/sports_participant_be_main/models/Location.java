package com.example.sports_participant_be_main.models;

import com.example.sports_participant_be_main.dto.LocationDto;
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
@Table(
        name = "locations",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"street", "street_number"})}
)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
@AllArgsConstructor
public class Location extends GlobalEntityProperties {

    @Id
    @GeneratedValue(generator = "hibernate-uuid")
    @GenericGenerator(name = "hibernate-uuid", strategy = "uuid2")
    @Column(name = "id", columnDefinition = "BINARY(16)")
    @EqualsAndHashCode.Include
    @ToString.Include
    private UUID id;

    @Column(name = "street", nullable = false)
    @EqualsAndHashCode.Include
    @ToString.Include
    private String street;

    @Column(name = "street_number", nullable = false)
    @EqualsAndHashCode.Include
    @ToString.Include
    private Integer streetNumber;

    @Column(name = "capacity", nullable = false)
    @EqualsAndHashCode.Include
    @ToString.Include
    private Integer capacity;

    @ManyToOne
    @JoinColumn(name = "gym_brand_id", referencedColumnName = "id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private GymBrand gymBrand;

    @ManyToMany(mappedBy = "locations")
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private Set<Client> clients = new HashSet<>();

    @Column(name = "status")
    @EqualsAndHashCode.Include
    @ToString.Include
    @Enumerated(EnumType.STRING)
    private Location.Status status;

    @OneToMany(mappedBy = "location")
    private Set<Appointment> appointments = new HashSet<>();

    @OneToMany(mappedBy = "location")
    private Set<LocationRoom> rooms = new HashSet<>();

    @OneToMany(mappedBy = "location")
    private Set<Schedule> schedules = new HashSet<>();

    @OneToMany(mappedBy = "location")
    private Set<Activity> activities = new HashSet<>();

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
                .gymBrandId(this.gymBrand.getId())
                .status(this.status)
                .build()
                ;
    }
}
