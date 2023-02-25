package com.example.sports_participant_be_main.models;

import com.example.sports_participant_be_main.dto.ClientDto;
import com.example.sports_participant_be_main.utils.GlobalEntityProperties;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "clients")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Client extends GlobalEntityProperties {

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
    @Column(name = "email", length=60, nullable = false)
    @EqualsAndHashCode.Include
    private String email;

    // Поки налл
    @Column(name = "password", nullable = true)
    @EqualsAndHashCode.Include
    private String password;

    @Column(name = "country")
    @EqualsAndHashCode.Include
    private String country;

    @Column(name = "city")
    @EqualsAndHashCode.Include
    private String city;

    @Column(name = "phone_number", nullable = false)
    @EqualsAndHashCode.Include
    private String phoneNumber;

    @Column(name = "status")
    @EqualsAndHashCode.Include
    @Enumerated(EnumType.STRING)
    private Client.Status status;

    @Column(name = "is_disabled", nullable = false)
    @EqualsAndHashCode.Include
    private Boolean is_disabled = false;

    // посилання на медичну карту якщо вона є, якщо ні, то це поле null

    // масив локацій (бо можуть бути різні підписки на послуги)
    @ManyToMany(cascade = CascadeType.REMOVE)
    @JoinTable(
            name = "client_locations",
            joinColumns = @JoinColumn(name = "client_id"),
            inverseJoinColumns = @JoinColumn(name = "location_id"))
    private Set<Location> locations = new HashSet<>();


    @AllArgsConstructor
    public enum Status {
        ACTIVE("ACTIVE"),
        BANNED("BANNED"),
        DISABLED("DISABLED"),
        FROZEN("FROZEN"),
        ;

        private final String value;
    }

    public ClientDto ofDto() {
        return ClientDto.builder()
                .id(this.id)
                .firstname(this.firstname)
                .lastname(this.lastname)
                .email(this.email)
                .password(this.password)
                .country(this.country)
                .city(this.city)
                .phoneNumber(this.phoneNumber)
                .status(this.status)
                .is_disabled(this.is_disabled)
                .build()
                ;
    }
}
