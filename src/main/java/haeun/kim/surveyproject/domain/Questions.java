package haeun.kim.surveyproject.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Questions extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "survey_id")
    private Surveys surveys; // 어떤 설문에 대한 질문인가?

    private String content;

    private Boolean choice; // true면 객관식 false면 주관식

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

}
