package com.example.sports_participant_be_main.models;

import com.example.sports_participant_be_main.dto.WishDto;
import com.example.sports_participant_be_main.utils.GlobalEntityProperties;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.UUID;

@Entity
@Table(name = "wishes")
@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
public class Wish extends GlobalEntityProperties {

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
    @Column(name = "email", length = 60, nullable = false)
    @EqualsAndHashCode.Include
    @ToString.Include
    private String email;

    @Column(name = "phone_number", nullable = false)
    @EqualsAndHashCode.Include
    @ToString.Include
    private String phoneNumber;

    @Column(name = "text", nullable = false, length = 2048)
    @EqualsAndHashCode.Include
    @ToString.Include
    private String text;

    @Column(name = "status")
    @EqualsAndHashCode.Include
    @ToString.Include
    @Enumerated(EnumType.STRING)
    private Wish.Status status;

    @ManyToOne
    @JoinColumn(name = "gym_brand_id", referencedColumnName = "id", nullable = false)
    private GymBrand gymBrand;

    @AllArgsConstructor
    public enum Status {
        PENDING("PENDING"),
        VIEWED("VIEWED"),
        NOT_VIEWED("NOT_VIEWED"),
        FROZEN("FROZEN"),
        ;

        private final String value;
    }

    public WishDto ofDto() {
        return WishDto.builder()
                .id(this.id)
                .firstname(this.firstname)
                .lastname(this.lastname)
                .email(this.email)
                .phoneNumber(this.phoneNumber)
                .text(this.text)
                .status(this.status)
                .build()
                ;
    }
}
