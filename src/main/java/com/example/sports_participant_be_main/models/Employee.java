package com.example.sports_participant_be_main.models;

import com.example.sports_participant_be_main.dto.EmployeeDto;
import com.example.sports_participant_be_main.security.Role;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.UUID;

@Entity
@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "employee")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Employee {

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

    @Column(name = "phone_number", nullable = false, unique = true)
    @EqualsAndHashCode.Include
    private String phoneNumber;

    @Column(name = "role")
    @EqualsAndHashCode.Include
    private Role role;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "gym_brand_id", referencedColumnName = "id", nullable = false)
    private GymBrand gymBrand;

    public EmployeeDto ofDto() {
        return EmployeeDto.builder()
                .id(this.id)
                .firstname(this.firstname)
                .lastname(this.lastname)
                .email(this.email)
                .password(this.password)
                .phoneNumber(this.phoneNumber)
                .role(this.role)
                .build()
                ;
    }
}
