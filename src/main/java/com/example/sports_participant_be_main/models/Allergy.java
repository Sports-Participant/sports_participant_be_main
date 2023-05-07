package com.example.sports_participant_be_main.models;

import com.example.sports_participant_be_main.dto.AllergyDto;
import com.example.sports_participant_be_main.utils.GlobalEntityProperties;
import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "allergy")
@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
public class Allergy extends GlobalEntityProperties {

    @Id
    @GeneratedValue(generator = "hibernate-uuid")
    @GenericGenerator(name = "hibernate-uuid", strategy = "uuid2")
    @Column(name = "id", columnDefinition = "BINARY(16)")
    @EqualsAndHashCode.Include
    @ToString.Include
    private UUID id;

    @Column(name = "name", nullable = false)
    @EqualsAndHashCode.Include
    @ToString.Include
    private String name;

    // todo set nullable = false
    @Column(name = "type", nullable = true)
    @EqualsAndHashCode.Include
    @ToString.Include
    @Enumerated(EnumType.STRING)
    private Allergy.Type type;

    @ElementCollection
    @CollectionTable(name = "allergy_symptoms", joinColumns = @JoinColumn(name = "allergy_id"))
    @Cascade(org.hibernate.annotations.CascadeType.REMOVE)
    private List<String> symptoms = new ArrayList<>();

    @Column(name = "severity", nullable = false)
    @EqualsAndHashCode.Include
    @ToString.Include
    @Enumerated(EnumType.STRING)
    private Allergy.Severity severity;

    @Column(name = "note", nullable = false, length = 1024)
    @EqualsAndHashCode.Include
    @ToString.Include
    private String note;

    @Column(name = "onset_date", nullable = false)
    @EqualsAndHashCode.Include
    @ToString.Include
    private LocalDate onsetDate;

    @ManyToOne
    @JoinColumn(name = "medical_card_id", referencedColumnName = "id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private MedicalCard medicalCard;

    @AllArgsConstructor
    public enum Type {
        ;

        private final String value;
    }

    @AllArgsConstructor
    public enum Severity {
        HIGH("HIGH"),
        MEDIUM("MEDIUM"),
        LOW("LOW"),
        ;

        private final String value;
    }

    public AllergyDto ofDto() {
        return AllergyDto.builder()
                .id(this.id)
                .name(this.name)
                .type(this.type)
                .symptoms(this.symptoms)
                .severity(this.severity)
                .note(this.note)
                .onsetDate(this.onsetDate)
                .symptoms(this.symptoms)
                .medicalCardId(this.medicalCard.getId())
                .build()
                ;
    }
}
