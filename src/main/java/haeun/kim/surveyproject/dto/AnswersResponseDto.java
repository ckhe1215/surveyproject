package haeun.kim.surveyproject.dto;

import haeun.kim.surveyproject.domain.Answers;
import lombok.Getter;

@Getter
public class AnswersResponseDto {
    private Long id;
    private Long questionId;
    private Boolean answer1; // 객관식 답
    private Boolean answer2;
    private Boolean answer3;
    private Boolean answer4;
    private Boolean answer5;
    private Boolean answer6;
    private Boolean answer7;
    private Boolean answer8;
    private Boolean answer9;
    private Boolean answer10;
    private String essayAnswer; // 주관식 답

    public AnswersResponseDto(Answers entity) {
        this.id = entity.getId();
        this.questionId = entity.getQuestionId();
        this.answer1 = entity.getAnswer1();
        this.answer2 = entity.getAnswer2();
        this.answer3 = entity.getAnswer3();
        this.answer4 = entity.getAnswer4();
        this.answer5 = entity.getAnswer5();
        this.answer6 = entity.getAnswer6();
        this.answer7 = entity.getAnswer7();
        this.answer8 = entity.getAnswer8();
        this.answer9 = entity.getAnswer9();
        this.answer10 = entity.getAnswer10();
        this.essayAnswer = entity.getEssayAnswer();
    }
}
