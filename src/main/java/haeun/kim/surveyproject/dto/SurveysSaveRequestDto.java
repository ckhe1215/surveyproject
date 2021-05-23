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
    private String subject;

    @Builder
    public SurveysSaveRequestDto(String title, String author, String subject) {
        this.title = title;
        this.author = author;
        this.subject = subject;
    }

    public Surveys toEntity() {
        return Surveys.builder()
                .title(title)
                .author(author)
                .subject(subject)
                .build();
    }
}
