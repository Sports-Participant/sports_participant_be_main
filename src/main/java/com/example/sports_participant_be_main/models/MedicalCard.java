package com.example.sports_participant_be_main.models;

import com.example.sports_participant_be_main.constants.HealthSupplier;
import com.example.sports_participant_be_main.dto.MedicalCardDto;
import com.example.sports_participant_be_main.utils.GlobalEntityProperties;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "medical_card")
@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class MedicalCard extends GlobalEntityProperties {

    @Id
    @GeneratedValue(generator = "hibernate-uuid")
    @GenericGenerator(name = "hibernate-uuid", strategy = "uuid2")
    @Column(name = "id", columnDefinition = "BINARY(16)")
    @EqualsAndHashCode.Include
    @ToString.Include
    private UUID id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "client_id", referencedColumnName = "id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Client client;

    @Column(name = "health_supplier", nullable = false)
    @EqualsAndHashCode.Include
    @ToString.Include
    @Enumerated(EnumType.STRING)
    private HealthSupplier healthSupplier;

    @OneToMany(mappedBy = "medicalCard")
    private Set<Allergy> allergies = new HashSet<>();

    @OneToMany(mappedBy = "medicalCard")
    private Set<Illness> illnesses = new HashSet<>();

    @OneToMany(mappedBy = "medicalCard")
    private Set<Disability> disabilities = new HashSet<>();

    @OneToMany(mappedBy = "medicalCard")
    private Set<MedicalRecord> medicalRecords = new HashSet<>();

    public MedicalCardDto ofDto() {
        return MedicalCardDto.builder()
                .id(this.id)
                .clientId(this.client.getId())
                .build()
                ;
    }
}
