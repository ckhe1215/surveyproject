package haeun.kim.surveyproject.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class PostsUpdateRequestDto {
    private String title;
    private String content;
    private Long surveyId;
    private int answerGoal;
    private LocalDateTime expiredDate;
    private boolean isExpired;

    @Builder
    public PostsUpdateRequestDto(String title, String content, Long surveyId, int answerGoal, LocalDateTime expiredDate, boolean isExpired) {
        this.title = title;
        this.content = content;
        this.surveyId = surveyId;
        this.answerGoal = answerGoal;
        this.expiredDate = expiredDate;
        this.isExpired = isExpired;
    }
}
