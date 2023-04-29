package com.example.sports_participant_be_main.models;

import com.example.sports_participant_be_main.dto.ActivityDto;
import com.example.sports_participant_be_main.utils.GlobalEntityProperties;
import javax.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "activities")
@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
// add rooms
public class Activity extends GlobalEntityProperties {

    @Id
    @GeneratedValue(generator = "hibernate-uuid")
    @GenericGenerator(name = "hibernate-uuid", strategy = "uuid2")
    @Column(name = "id", columnDefinition = "BINARY(16)")
    @EqualsAndHashCode.Include
    @ToString.Include
    private UUID id;

    @Column(name = "title", nullable = false)
    @EqualsAndHashCode.Include
    @ToString.Include
    private String title;

    @Column(name = "description")
    @EqualsAndHashCode.Include
    @ToString.Include
    private String description;

    @OneToMany(mappedBy = "activity", cascade = CascadeType.ALL)
    private Set<ActivityPrice> activityPrices = new HashSet<>();

    @OneToMany(mappedBy = "activity", cascade = CascadeType.ALL)
    private Set<Appointment> appointments = new HashSet<>();

    @ManyToMany(cascade = CascadeType.REMOVE)
    @JoinTable(
            name = "activity_employee",
            joinColumns = @JoinColumn(name = "activity_id"),
            inverseJoinColumns = @JoinColumn(name = "employee_id")
    )
    private Set<Employee> employees = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "location_id", referencedColumnName = "id", nullable = false)
//    @OnDelete(action = OnDeleteAction.CASCADE)
    private Location location;

    public ActivityDto ofDto() {
        return ActivityDto.builder()
                .id(this.id)
                .title(this.title)
                .description(this.description)
                .locationId(this.location.getId())
                .build()
                ;
    }
}
