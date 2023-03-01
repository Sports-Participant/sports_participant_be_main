package com.example.sports_participant_be_main.models;

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
public class Activity extends GlobalEntityProperties {

    @Id
    @GeneratedValue(generator = "hibernate-uuid")
    @GenericGenerator(name = "hibernate-uuid", strategy = "uuid2")
    @Column(name = "id", columnDefinition = "BINARY(16)")
    @EqualsAndHashCode.Include
    private UUID id;

    @Column(name = "title", nullable = false)
    @EqualsAndHashCode.Include
    private String title;

    @Column(name = "description")
    @EqualsAndHashCode.Include
    private String description;

    @OneToMany(mappedBy = "activity")
    private Set<ActivityPrice> activityPrices = new HashSet<>();

    @OneToMany(mappedBy = "activity")
    private Set<Appointment> appointments = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "activity_employee",
            joinColumns = @JoinColumn(name = "activity_id"),
            inverseJoinColumns = @JoinColumn(name = "employee_id")
    )
    private Set<Employee> employees = new HashSet<>();

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "location_id", referencedColumnName = "id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Location location;
}
