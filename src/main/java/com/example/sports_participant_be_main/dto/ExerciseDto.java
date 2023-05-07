package com.example.sports_participant_be_main.dto;

import com.example.sports_participant_be_main.models.Exercise;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class ExerciseDto {

    private UUID id;

    @NotNull
    private String title;

    @NotNull
    private String text;

    private Exercise.Disability disability;

    private List<String> urls;

    public Exercise ofEntity(){
        return Exercise.builder()
                .id(this.id)
                .title(this.title)
                .text(this.text)
                .disability(this.disability)
                .urls(this.urls)
                .build()
                ;
    }
}
