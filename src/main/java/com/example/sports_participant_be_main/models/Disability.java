package com.example.sports_participant_be_main.models;

import com.example.sports_participant_be_main.dto.DisabilityDto;
import com.example.sports_participant_be_main.utils.GlobalEntityProperties;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "disability")
@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Disability extends GlobalEntityProperties {

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

    @Column(name = "severity", nullable = false)
    @EqualsAndHashCode.Include
    @ToString.Include
    @Enumerated(EnumType.STRING)
    private Disability.Severity severity;

    @Column(name = "note", length = 1024, nullable = false)
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
    public enum Severity {
        HIGH("HIGH"),
        MEDIUM("MEDIUM"),
        LOW("LOW"),
        ;

        private final String value;
    }

    public DisabilityDto ofDto() {
        return DisabilityDto.builder()
                .id(this.id)
                .name(this.name)
                .severity(this.severity)
                .note(this.note)
                .onsetDate(this.onsetDate)
                .medicalCardId(this.medicalCard.getId())
                .build()
                ;
    }
}
