package haeun.kim.surveyproject.dto;

import haeun.kim.surveyproject.domain.Questions;
import haeun.kim.surveyproject.domain.Surveys;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class QuestionsSaveRequestDto {

    private Long surveyId; // 어떤 설문에 대한 질문인가?
    private String content;
    private Boolean choicable; // true면 객관식 false면 주관식
    private Boolean multiple; // true면 중복답 허용
    private Boolean etcAnswer; // 객관식 마지막 보기를 기타(주관식)으로 추가
    private Boolean necessaryAns; // 필수로 답해야하는 문항인지
    private int choiceCnt; // 객관식 답안 갯수 (max : 10)
    private String choice1; // 객관식 보기 1번
    private String choice2;
    private String choice3;
    private String choice4;
    private String choice5;
    private String choice6;
    private String choice7;
    private String choice8;
    private String choice9;
    private String choice10;

    @Builder
    public QuestionsSaveRequestDto(Long surveyId, String content, Boolean choicable, Boolean multiple, Boolean etcAnswer,
                     Boolean necessaryAns, int choiceCnt, String choice1, String choice2, String choice3,
                     String choice4, String choice5, String choice6, String choice7, String choice8,
                     String choice9, String choice10)
    {
        this.surveyId = surveyId;
        this.content = content;
        this.choicable = choicable;
        this.multiple = multiple;
        this.etcAnswer = etcAnswer;
        this.necessaryAns = necessaryAns;
        this.choiceCnt = choiceCnt;
        this.choice1 = choice1;
        this.choice2 = choice2;
        this.choice3 = choice3;
        this.choice4 = choice4;
        this.choice5 = choice5;
        this.choice6 = choice6;
        this.choice7 = choice7;
        this.choice8 = choice8;
        this.choice9 = choice9;
        this.choice10 = choice10;
    }
    public Questions toEntity(){
        return Questions.builder()
                .surveyId(surveyId)
                .content(content)
                .choicable(choicable)
                .multiple(multiple)
                .etcAnswer(etcAnswer)
                .necessaryAns(necessaryAns)
                .choiceCnt(choiceCnt)
                .choice1(choice1)
                .choice2(choice2)
                .choice3(choice3)
                .choice4(choice4)
                .choice5(choice5)
                .choice6(choice6)
                .choice7(choice7)
                .choice8(choice8)
                .choice9(choice9)
                .choice10(choice10)
                .build();
    }
}
