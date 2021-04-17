package haeun.kim.surveyproject.dto;

import haeun.kim.surveyproject.domain.Surveys;
import lombok.Getter;

@Getter
public class SurveysListResponseDto {
    private Long id;
    private String title;

    public SurveysListResponseDto(Surveys entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
    }
}
