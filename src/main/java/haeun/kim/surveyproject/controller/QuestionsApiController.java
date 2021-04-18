package haeun.kim.surveyproject.controller;

import haeun.kim.surveyproject.dto.QuestionsSaveRequestDto;
import haeun.kim.surveyproject.service.QuestionsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class QuestionsApiController {

    private final QuestionsService questionsService;

    @PostMapping("/api/v1/questions")
    public Long save(@RequestBody QuestionsSaveRequestDto requestDto) {
        return questionsService.save(requestDto);
    }
}
