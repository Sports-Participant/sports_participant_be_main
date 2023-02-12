package com.example.sports_participant_be_main.models;

import com.example.sports_participant_be_main.dto.GymBrandDto;
import com.example.sports_participant_be_main.utils.GlobalEntityProperties;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Entity
@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "gym_brands")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class GymBrand extends GlobalEntityProperties {

    @Id
    @GeneratedValue(generator = "hibernate-uuid")
    @GenericGenerator(name = "hibernate-uuid", strategy = "uuid2")
    @Column(name = "id", columnDefinition = "BINARY(16)")
    @EqualsAndHashCode.Include
    private UUID id;

    @Column(name = "name", unique = true, nullable = false)
    @EqualsAndHashCode.Include
    private String name;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "owner_id", referencedColumnName = "id", nullable = false)
    private Owner owner;

    @OneToMany(mappedBy = "gymBrand")
    private Set<Location> locations = new HashSet<>();

    @OneToMany(mappedBy = "gymBrand")
    private Set<Employee> employees = new HashSet<>();

    public GymBrandDto ofDto() {
        return GymBrandDto.builder()
                .id(this.id)
                .name(this.name)
                .ownerId(this.owner.getId())
                .build()
                ;
    }
}
