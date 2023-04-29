package com.example.sports_participant_be_main.models;

import com.example.sports_participant_be_main.dto.EmployeeDto;
import com.example.sports_participant_be_main.utils.GlobalEntityProperties;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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
@Table(name = "employee")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
public class Employee extends GlobalEntityProperties {

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

    @Column(name = "password", nullable = false)
    @EqualsAndHashCode.Include
    @ToString.Include
    private String password;

    @Column(name = "phone_number", nullable = false, unique = true)
    @EqualsAndHashCode.Include
    @ToString.Include
    private String phoneNumber;

    @ManyToMany()
    @JoinTable (
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "gym_brand_id", referencedColumnName = "id", nullable = false)
//    @OnDelete(action = OnDeleteAction.CASCADE)
    private GymBrand gymBrand;

    @Column(name = "status")
    @EqualsAndHashCode.Include
    @Enumerated(EnumType.STRING)
    @ToString.Include
    private Employee.Status status;

    @ManyToMany(cascade = CascadeType.REMOVE)
    private Set<Activity> activities = new HashSet<>();

    @OneToMany(mappedBy = "employee", cascade = CascadeType.REMOVE)
    private Set<Appointment> appointments = new HashSet<>();

    public void removeRole(Role role){
        this.roles.remove(role);
    }

    @AllArgsConstructor
    public enum Status {
        ACTIVE("ACTIVE"),
        BANNED("BANNED"),
        DISABLED("DISABLED"),
        FROZEN("FROZEN"),
        ;

        private final String value;
    }

    public EmployeeDto ofDto() {
        return EmployeeDto.builder()
                .id(this.id)
                .firstname(this.firstname)
                .lastname(this.lastname)
                .email(this.email)
                .password(this.password)
                .phoneNumber(this.phoneNumber)
                .status(this.status)
                .build()
                ;
    }
}
