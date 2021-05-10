package haeun.kim.surveyproject.controller;

import haeun.kim.surveyproject.dto.AnswersResponseDto;
import haeun.kim.surveyproject.dto.PostsListResponseDto;
import haeun.kim.surveyproject.dto.QuestionsResponseDto;
import haeun.kim.surveyproject.dto.SurveysSaveRequestDto;
import haeun.kim.surveyproject.service.AnswersService;
import haeun.kim.surveyproject.service.PostsService;
import haeun.kim.surveyproject.service.QuestionsService;
import haeun.kim.surveyproject.service.SurveysService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class SurveysApiController {

    private final SurveysService surveysService;
    private final PostsService postsService;
    private final QuestionsService questionsService;
    private final AnswersService answersService;

    @PostMapping("/api/v1/surveys")
    public Long save(@RequestBody SurveysSaveRequestDto requestDto) {
        return surveysService.save(requestDto);
    }

    @DeleteMapping("/api/v1/surveys/{id}")
    public Long delete(@PathVariable Long id) {
        // 이 설문을 포함하고 있는 게시글 모두 지우기
        List<PostsListResponseDto> posts = postsService.findAllBySurveyId(id);
        for(PostsListResponseDto post : posts) {
            postsService.delete(post.getId());
        }
        // 이 설문이 가지고 있는 질문의 답변 모두 지우기
        List<QuestionsResponseDto> questions = questionsService.findBySurveyId(id);
        for (QuestionsResponseDto question : questions) {
            List<AnswersResponseDto> answers = answersService.findByQuestionId(question.getId());
            for (AnswersResponseDto answer : answers) {
                answersService.delete(answer.getId());
            }
        }
        // 이 설문이 가지고 있는 질문 모두 지우기
        for (QuestionsResponseDto question : questions) {
            questionsService.delete(question.getId());
        }

        surveysService.delete(id);
        return id;
    }
}
