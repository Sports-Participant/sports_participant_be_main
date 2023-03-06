package com.example.sports_participant_be_main.models;

import com.example.sports_participant_be_main.dto.RoleDto;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "roles")
@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Role {

    @Id
    @GeneratedValue(generator = "hibernate-uuid")
    @GenericGenerator(name = "hibernate-uuid", strategy = "uuid2")
    @Column(name = "id", columnDefinition = "BINARY(16)")
    @EqualsAndHashCode.Include
    private UUID id;

    @Column(name = "name", nullable = false, unique = true)
    @EqualsAndHashCode.Include
    private String name;

    @OneToMany(mappedBy = "role")
    private Set<Owner> owners = new HashSet<>();

//    @ManyToMany(cascade = CascadeType.ALL)
//    @JoinTable (
//            name = "users_roles",
//            joinColumns = @JoinColumn(name = "user_id"),
//            inverseJoinColumns = @JoinColumn(name = "role_id")
//
//    )
//    private Set<Owner> owners = new HashSet<>();
//
//    @ManyToMany(cascade = {CascadeType.REMOVE})
//    @JoinTable (
//            name = "users_roles",
//            joinColumns = @JoinColumn(name = "user_id"),
//            inverseJoinColumns = @JoinColumn(name = "role_id")
//
//    )
    @ManyToMany
    private Set<Employee> employees = new HashSet<>();

    public RoleDto ofDto(){
        return RoleDto.builder()
                .id(this.id)
                .name(this.name)
                .build()
                ;
    }
}


