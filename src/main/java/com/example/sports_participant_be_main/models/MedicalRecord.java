package com.example.sports_participant_be_main.models;

import com.example.sports_participant_be_main.dto.MedicalRecordDto;
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
@Table(name = "medical_record")
@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class MedicalRecord extends GlobalEntityProperties {

    @Id
    @GeneratedValue(generator = "hibernate-uuid")
    @GenericGenerator(name = "hibernate-uuid", strategy = "uuid2")
    @Column(name = "id", columnDefinition = "BINARY(16)")
    @EqualsAndHashCode.Include
    @ToString.Include
    private UUID id;

    @Column(name = "record_number")
    @EqualsAndHashCode.Include
    @ToString.Include
    private Integer recordNumber;

    @Column(name = "title", nullable = false)
    @EqualsAndHashCode.Include
    @ToString.Include
    private String title;

    @Column(name = "text", nullable = false, length = 1024)
    @EqualsAndHashCode.Include
    @ToString.Include
    private String text;

    @Column(name = "date", nullable = false)
    @EqualsAndHashCode.Include
    @ToString.Include
    private LocalDate date;

    @ElementCollection
    @CollectionTable(name = "current_medications", joinColumns = @JoinColumn(name = "medical_record_id"))
    @Cascade(org.hibernate.annotations.CascadeType.REMOVE)
    private List<String> currentMedications = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "medical_card_id", referencedColumnName = "id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private MedicalCard medicalCard;

    public MedicalRecordDto ofDto() {
        return MedicalRecordDto.builder()
                .id(this.id)
                .recordNumber(this.recordNumber)
                .title(this.title)
                .text(this.text)
                .date(this.date)
                .currentMedications(this.currentMedications)
                .medicalCardId(this.medicalCard.getId())
                .build()
                ;
    }
}
