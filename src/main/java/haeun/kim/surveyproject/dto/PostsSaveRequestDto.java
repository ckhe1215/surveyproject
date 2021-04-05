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
    private String author;
    private Long surveyId;
    private int answerGoal;
    private LocalDateTime expiredDate;
    private boolean isExpired;

    @Builder
    public PostsSaveRequestDto(String title, String content, String author, Long surveyId, int answerGoal, LocalDateTime expiredDate, boolean isExpired) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.surveyId = surveyId;
        this.answerGoal = answerGoal;
        this.expiredDate = expiredDate;
        this.isExpired = isExpired;
    }
    public Posts toEntity() {
        return Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .surveyId(surveyId)
                .answerGoal(answerGoal)
                .expiredDate(expiredDate)
                .isExpired(isExpired)
                .build();
    }
}
