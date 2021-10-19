package haeun.kim.surveyproject.dto;

import haeun.kim.surveyproject.domain.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class PostsSaveRequestDto {
    private String title;
    private String content;
    private String subject;
    private String author;
    private String authorEmail;
    private int answerGoal;
    private LocalDateTime expiredDate;

    @Builder
    public PostsSaveRequestDto(String title, String content, String subject, String author,
                               String authorEmail, int answerGoal, LocalDateTime expiredDate) {
        this.title = title;
        this.content = content;
        this.subject = subject;
        this.author = author;
        this.authorEmail = authorEmail;
        this.answerGoal = answerGoal;
        this.expiredDate = expiredDate;
    }
    public Posts toEntity() {
        return Posts.builder()
                .title(title)
                .content(content)
                .subject(subject)
                .author(author)
                .authorEmail(authorEmail)
                .answerGoal(answerGoal)
                .expiredDate(expiredDate)
                .build();
    }
}
