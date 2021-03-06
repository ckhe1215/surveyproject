package haeun.kim.surveyproject.dto;

import haeun.kim.surveyproject.domain.Posts;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostsResponseDto {
    private Long id;
    private String title;
    private String content;
    private String subject;
    private String author;
    private String authorEmail;
    private int answerGoal;
    private LocalDateTime expiredDate;

    public PostsResponseDto(Posts entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.subject = entity.getSubject();
        this.author = entity.getAuthor();
        this.authorEmail = entity.getAuthorEmail();
        this.answerGoal = entity.getAnswerGoal();
        this.expiredDate = entity.getExpiredDate();
    }
}
