package com.example.sports_participant_be_main.models;

import com.example.sports_participant_be_main.dto.GymBrandDto;
import com.example.sports_participant_be_main.utils.GlobalEntityProperties;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "gym_brands")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
public class GymBrand extends GlobalEntityProperties {

    @Id
    @GeneratedValue(generator = "hibernate-uuid")
    @GenericGenerator(name = "hibernate-uuid", strategy = "uuid2")
    @Column(name = "id", columnDefinition = "BINARY(16)")
    @EqualsAndHashCode.Include
    @ToString.Include
    private UUID id;

    @Column(name = "name", unique = true, nullable = false)
    @EqualsAndHashCode.Include
    @ToString.Include
    private String name;

    @ManyToOne
    @JoinColumn(name = "owner_id", referencedColumnName = "id", nullable = false)
    private Owner owner;

    @OneToMany(mappedBy = "gymBrand", cascade = CascadeType.REMOVE)
    private Set<Location> locations = new HashSet<>();

    @OneToMany(mappedBy = "gymBrand", cascade = CascadeType.REMOVE)
    private Set<Wish> wishes = new HashSet<>();

    @OneToMany(mappedBy = "gymBrand", cascade = CascadeType.REMOVE)
    private Set<Employee> employees = new HashSet<>();

    @Column(name = "status")
    @EqualsAndHashCode.Include
    @ToString.Include
    @Enumerated(EnumType.STRING)
    private GymBrand.Status status;

    @AllArgsConstructor
    public enum Status {
        ACTIVE("ACTIVE"),
        BANNED("BANNED"),
        DISABLED("DISABLED"),
        FROZEN("FROZEN"),
        ;

        private final String value;
    }

    public GymBrandDto ofDto() {
        return GymBrandDto.builder()
                .id(this.id)
                .name(this.name)
                .ownerId(this.owner.getId())
                .status(this.status)
                .build()
                ;
    }
}
