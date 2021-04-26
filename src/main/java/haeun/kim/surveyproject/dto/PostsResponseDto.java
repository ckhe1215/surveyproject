package haeun.kim.surveyproject.dto;

import haeun.kim.surveyproject.domain.Posts;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostsResponseDto {
    private Long id;
    private String title;
    private String content;
    private String author;
    private String authorEmail;
    private Long surveyId;
    private int answerGoal;
    private LocalDateTime expiredDate;
    private boolean isExpired;

    public PostsResponseDto(Posts entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.author = entity.getAuthor();
        this.authorEmail = entity.getAuthorEmail();
        this.surveyId = entity.getSurveyId();
        this.answerGoal = entity.getAnswerGoal();
        this.expiredDate = entity.getExpiredDate();
        this.isExpired = entity.isExpired();
    }
}
