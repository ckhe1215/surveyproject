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

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Questions questions;

    private int choiceAnswer; // 객관식 답

    private String essayAnswer; // 주관식 답

    @Builder
    public Answers(Questions questions, int choiceAnswer, String essayAnswer){
        this.questions = questions;
        this.choiceAnswer = choiceAnswer;
        this.essayAnswer = essayAnswer;
    }
}
