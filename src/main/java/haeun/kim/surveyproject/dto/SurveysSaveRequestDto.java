package haeun.kim.surveyproject.dto;

import haeun.kim.surveyproject.domain.Surveys;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SurveysSaveRequestDto {
    private String title;
    private String author;

    @Builder
    public SurveysSaveRequestDto(String title, String author) {
        this.title = title;
        this.author = author;
    }

    public Surveys toEntity() {
        return Surveys.builder()
                .title(title)
                .author(author)
                .build();
    }
}
