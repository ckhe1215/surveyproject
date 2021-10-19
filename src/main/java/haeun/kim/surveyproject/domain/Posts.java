package haeun.kim.surveyproject.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
public class Posts extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Column(nullable = false)
    private String subject;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    private String authorEmail;

    @Column(nullable = false)
    private int answerGoal;

    @Column(nullable = false)
    private LocalDateTime expiredDate;

    @Builder
    public Posts(String title, String content, String subject, String author, String authorEmail,
                 int answerGoal, LocalDateTime expiredDate) {
        this.title = title;
        this.content = content;
        this.subject = subject;
        this.author = author;
        this.authorEmail = authorEmail;
        this.answerGoal = answerGoal;
        this.expiredDate = expiredDate;
    }

    public void update(String title, String content, int answerGoal, LocalDateTime expiredDate) {
        this.title = title;
        this.content = content;
        this.answerGoal = answerGoal;
        this.expiredDate = expiredDate;
    }
}
