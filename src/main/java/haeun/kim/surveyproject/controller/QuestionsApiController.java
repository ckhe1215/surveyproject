package haeun.kim.surveyproject.controller;

import haeun.kim.surveyproject.dto.AnswersResponseDto;
import haeun.kim.surveyproject.dto.QuestionsSaveRequestDto;
import haeun.kim.surveyproject.service.AnswersService;
import haeun.kim.surveyproject.service.QuestionsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class QuestionsApiController {

    private final QuestionsService questionsService;
    private final AnswersService answersService;

    @PostMapping("/api/v1/questions")
    public Long save(@RequestBody QuestionsSaveRequestDto requestDto) {
        return questionsService.save(requestDto);
    }

    @DeleteMapping("api/v1/questions/{id}")
    public Long delete(@PathVariable Long id) {
        // 질문에 대한 답변 모두 삭제
        List<AnswersResponseDto> answers = answersService.findByQuestionId(id);
        for (AnswersResponseDto answer : answers) {
            answersService.delete(answer.getId());
        }
        // 질문 삭제
        questionsService.delete(id);
        return id;
    }
}
