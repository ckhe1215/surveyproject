package haeun.kim.surveyproject.dto;

import haeun.kim.surveyproject.domain.Answers;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AnswersSaveRequestDto {
    private Long questionId;
    private Boolean answer1;
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

    @Builder
    public AnswersSaveRequestDto(Long questionId, Boolean answer1, Boolean answer2, Boolean answer3, Boolean answer4, Boolean answer5,
                                 Boolean answer6, Boolean answer7, Boolean answer8, Boolean answer9, Boolean answer10, String essayAnswer){
        this.questionId = questionId;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.answer3 = answer3;
        this.answer4 = answer4;
        this.answer5 = answer5;
        this.answer6 = answer6;
        this.answer7 = answer7;
        this.answer8 = answer8;
        this.answer9 = answer9;
        this.answer10 = answer10;
        this.essayAnswer = essayAnswer;
    }

    public Answers toEntity(){
        return Answers.builder()
                .questionId(questionId)
                .answer1(answer1)
                .answer2(answer2)
                .answer3(answer3)
                .answer4(answer4)
                .answer5(answer5)
                .answer6(answer6)
                .answer7(answer7)
                .answer8(answer8)
                .answer9(answer9)
                .answer10(answer10)
                .essayAnswer(essayAnswer)
                .build();
    }
}
