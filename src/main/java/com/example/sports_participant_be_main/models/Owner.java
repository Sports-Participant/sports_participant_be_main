package com.example.sports_participant_be_main.models;

import com.example.sports_participant_be_main.security.Role;
import com.example.sports_participant_be_main.utils.GlobalEntityProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "owners",
        indexes = {
            @Index(name = "email_index", columnList = "email")
        })
@Data
@EqualsAndHashCode(callSuper = true)
public class Owner extends GlobalEntityProperties {

    @Id
    @GeneratedValue(generator = "hibernate-uuid")
    @GenericGenerator(name = "hibernate-uuid", strategy = "uuid2")
    @Column(name = "id", columnDefinition = "BINARY(16)")
    private UUID id;

    @Column(name = "firstname", nullable = false, length = 20)
    private String firstname;

    @Column(name = "lastname", nullable = false, length = 20)
    private String lastname;

    @Email(regexp = ".+@.+\\..+", message = "Invalid email format")
    @Column(name = "email", length=60, nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "country")
    private String country;

    @Column(name = "city")
    private String city;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "role", updatable = false)
    private Role role;
}
