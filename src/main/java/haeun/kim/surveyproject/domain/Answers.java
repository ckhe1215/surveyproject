package haeun.kim.surveyproject.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Answers extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long questionId;

    private int choiceAnswer; // 객관식 답

    private String essayAnswer; // 주관식 답

    @Builder
    public Answers(Long questionId, int choiceAnswer, String essayAnswer){
        this.questionId = questionId;
        this.choiceAnswer = choiceAnswer;
        this.essayAnswer = essayAnswer;
    }
}
