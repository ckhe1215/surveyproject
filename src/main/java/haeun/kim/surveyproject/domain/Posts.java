package haeun.kim.surveyproject.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
public class Posts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    private Long surveyId;

    @Column(nullable = false)
    private int answerGoal;

    @Column(nullable = false)
    private LocalDateTime expiredDate;

    @Column(nullable = false)
    private boolean isExpired;

    @Builder
    public Posts(String title, String content, String author, Long surveyId, int answerGoal, LocalDateTime expiredDate, boolean isExpired) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.surveyId = surveyId;
        this.answerGoal = answerGoal;
        this.expiredDate = expiredDate;
        this.isExpired = isExpired;
    }
}
