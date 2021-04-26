package haeun.kim.surveyproject.dto;

import haeun.kim.surveyproject.domain.Surveys;
import lombok.Getter;

import javax.persistence.Column;

@Getter
public class SurveysResponseDto {
    private Long id;
    private String title;
    private String author;

    public SurveysResponseDto(Surveys entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.author = entity.getAuthor();
    }
}
