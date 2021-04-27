package haeun.kim.surveyproject.controller;

import haeun.kim.surveyproject.dto.AnswersSaveRequestDto;
import haeun.kim.surveyproject.service.AnswersService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class AnswerApiController {

    private final AnswersService answersService;

    @PostMapping("/api/v1/answers")
    public Long save(@RequestBody AnswersSaveRequestDto requestDto) {
        return answersService.save(requestDto);
    }
}
