package com.example.sports_participant_be_main.models;

import com.example.sports_participant_be_main.dto.LocationDto;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
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
public class Location {

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

    public LocationDto ofDto() {
        return LocationDto.builder()
                .id(this.id)
                .street(this.street)
                .streetNumber(streetNumber)
                .capacity(capacity)
                .gymBrandId(this.gymBrand.getId())
                .build()
                ;
    }
}
