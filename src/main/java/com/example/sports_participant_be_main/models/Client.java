package com.example.sports_participant_be_main.models;

import com.example.sports_participant_be_main.dto.ClientDto;
import com.example.sports_participant_be_main.utils.GlobalEntityProperties;
import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.time.LocalDate;
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
@ToString(onlyExplicitlyIncluded = true)
public class Client extends GlobalEntityProperties {

    @Id
    @GeneratedValue(generator = "hibernate-uuid")
    @GenericGenerator(name = "hibernate-uuid", strategy = "uuid2")
    @Column(name = "id", columnDefinition = "BINARY(16)")
    @EqualsAndHashCode.Include
    @ToString.Include
    private UUID id;

    @Column(name = "firstname", nullable = false, length = 20)
    @EqualsAndHashCode.Include
    @ToString.Include
    private String firstname;

    @Column(name = "lastname", nullable = false, length = 20)
    @EqualsAndHashCode.Include
    @ToString.Include
    private String lastname;

    @Email(regexp = ".+@.+\\..+", message = "Invalid email format")
    @Column(name = "email", length=60, nullable = false)
    @EqualsAndHashCode.Include
    @ToString.Include
    private String email;

    // Поки налл
    @Column(name = "password", nullable = true)
    @EqualsAndHashCode.Include
    @ToString.Include
    private String password;

    @Column(name = "country")
    @EqualsAndHashCode.Include
    @ToString.Include
    private String country;

    @Column(name = "city")
    @EqualsAndHashCode.Include
    @ToString.Include
    private String city;

    @Column(name = "phone_number", nullable = false)
    @EqualsAndHashCode.Include
    @ToString.Include
    private String phoneNumber;

    @Column(name = "status")
    @EqualsAndHashCode.Include
    @ToString.Include
    @Enumerated(EnumType.STRING)
    private Client.Status status;

    @Column(name = "is_disabled", nullable = false)
    @EqualsAndHashCode.Include
    @ToString.Include
    private Boolean isDisabled = false;

    @Column(name = "dob", nullable = false)
    @EqualsAndHashCode.Include
    @ToString.Include
    private LocalDate dob;

    @Column(name = "gender", nullable = false)
    @EqualsAndHashCode.Include
    @ToString.Include
    @Enumerated(EnumType.STRING)
    private Client.Gender gender;

    @OneToOne(mappedBy = "client", cascade = CascadeType.REMOVE)
    private MedicalCard medicalCard;

    @OneToMany(mappedBy = "client", cascade = CascadeType.REMOVE)
    private Set<Appointment> appointments = new HashSet<>();

    // посилання на медичну карту якщо вона є, якщо ні, то це поле null

    // масив локацій (бо можуть бути різні підписки на послуги)
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "client_locations",
            joinColumns = @JoinColumn(name = "client_id"),
            inverseJoinColumns = @JoinColumn(name = "location_id", referencedColumnName = "id"))
//    @Cascade(org.hibernate.annotations.CascadeType.REMOVE)
    private Set<Location> locations = new HashSet<>();

//    @PreRemove
//    private void remove() {
//        this.locations.clear();
//    }


    @AllArgsConstructor
    public enum Status {
        ACTIVE("ACTIVE"),
        PAUSED("PAUSED"),
        PENDING("PENDING"),
        BLOCKED("BLOCKED"),
        ;

        private final String value;
    }

    @AllArgsConstructor
    public enum Gender {
        MALE("MALE"),
        FEMALE("FEMALE"),
        OTHER("OTHER"),
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
                .isDisabled(this.isDisabled)
                .dob(this.dob)
                .gender(this.gender)
                .build()
                ;
    }
}
