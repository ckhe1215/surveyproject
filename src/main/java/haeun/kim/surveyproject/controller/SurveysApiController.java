package haeun.kim.surveyproject.controller;

import haeun.kim.surveyproject.dto.SurveysSaveRequestDto;
import haeun.kim.surveyproject.service.SurveysService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class SurveysApiController {

    private final SurveysService surveysService;

    @PostMapping("/api/v1/surveys")
    public Long save(@RequestBody SurveysSaveRequestDto requestDto) {
        return surveysService.save(requestDto);
    }
}
