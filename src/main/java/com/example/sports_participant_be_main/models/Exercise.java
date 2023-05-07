package com.example.sports_participant_be_main.models;

import com.example.sports_participant_be_main.dto.ExerciseDto;
import com.example.sports_participant_be_main.utils.GlobalEntityProperties;
import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "exercise")
@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
public class Exercise extends GlobalEntityProperties {

    @Id
    @GeneratedValue(generator = "hibernate-uuid")
    @GenericGenerator(name = "hibernate-uuid", strategy = "uuid2")
    @Column(name = "id", columnDefinition = "BINARY(16)")
    @EqualsAndHashCode.Include
    @ToString.Include
    private UUID id;

    @Column(name = "title", nullable = false, length = 100)
    @EqualsAndHashCode.Include
    @ToString.Include
    private String title;

    @Column(name = "text", nullable = false, length = 1024)
    @EqualsAndHashCode.Include
    @ToString.Include
    private String text;

    @Column(name = "disability")
    @EqualsAndHashCode.Include
    @ToString.Include
    @Enumerated(EnumType.STRING)
    private Exercise.Disability disability;

    @ElementCollection
    @CollectionTable(name = "exercise_urls", joinColumns = @JoinColumn(name = "url_id"))
    @Cascade(org.hibernate.annotations.CascadeType.REMOVE)
    private List<String> urls = new ArrayList<>();

    @AllArgsConstructor
    public enum Disability {
        ABSENCE_OF_HAND("ABSENCE_OF_HAND"),
        ABSENCE_OF_LEG("ABSENCE_OF_LEG"),
        NO_LEGS("NO_LEGS"),
        NO_HANDS("NO_HANDS"),
        ;

        private final String value;
    }

    public ExerciseDto ofDto() {
        return ExerciseDto.builder()
                .id(this.id)
                .title(this.title)
                .text(this.text)
                .disability(this.disability)
                .urls(this.urls)
                .build()
                ;
    }


}
