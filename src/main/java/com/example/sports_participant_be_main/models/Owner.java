package com.example.sports_participant_be_main.models;

import com.example.sports_participant_be_main.dto.OwnerDto;
import com.example.sports_participant_be_main.security.Role;
import com.example.sports_participant_be_main.utils.GlobalEntityProperties;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Entity
@Table(name = "owners",
        indexes = {
            @Index(name = "email_index", columnList = "email")
        })
@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Owner extends GlobalEntityProperties {

    @Id
    @GeneratedValue(generator = "hibernate-uuid")
    @GenericGenerator(name = "hibernate-uuid", strategy = "uuid2")
    @Column(name = "id", columnDefinition = "BINARY(16)")
    @EqualsAndHashCode.Include
    private UUID id;

    @Column(name = "firstname", nullable = false, length = 20)
    @EqualsAndHashCode.Include
    private String firstname;

    @Column(name = "lastname", nullable = false, length = 20)
    @EqualsAndHashCode.Include
    private String lastname;

    @Email(regexp = ".+@.+\\..+", message = "Invalid email format")
    @Column(name = "email", length=60, nullable = false, unique = true)
    @EqualsAndHashCode.Include
    private String email;

    @Column(name = "password", nullable = false)
    @EqualsAndHashCode.Include
    private String password;

    @Column(name = "country")
    @EqualsAndHashCode.Include
    private String country;

    @Column(name = "city")
    @EqualsAndHashCode.Include
    private String city;

    @Column(name = "phone_number", nullable = false, unique = true)
    @EqualsAndHashCode.Include
    private String phoneNumber;

    @Column(name = "role", updatable = false)
    private Role role;

    @OneToMany(mappedBy = "owner")
    private Set<GymBrand> gymBrands = new HashSet<>();

    public OwnerDto ofDto() {
        return OwnerDto.builder()
                .id(this.id)
                .firstname(this.firstname)
                .lastname(this.lastname)
                .email(this.email)
                .password(this.password)
                .country(this.country)
                .city(this.city)
                .phoneNumber(this.phoneNumber)
                .role(this.role)
                .gymBrandsId(this.gymBrands.stream()
                        .map(GymBrand::getId)
                        .collect(Collectors.toList()))
                .build()
                ;
    }
}
