package haeun.kim.surveyproject.controller;

import haeun.kim.surveyproject.domain.Surveys;
import haeun.kim.surveyproject.dto.QuestionsResponseDto;
import haeun.kim.surveyproject.dto.SurveysResponseDto;
import haeun.kim.surveyproject.service.QuestionsService;
import haeun.kim.surveyproject.service.SurveysService;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class AnswersController {

    private final QuestionsService questionsService;

    @GetMapping("/answers/save/{survey_id}")
    public String answersSave(@PathVariable Long survey_id, Model model){
        model.addAttribute("questions", questionsService.findBySurveyId(survey_id));
        model.addAttribute("questionsSize", questionsService.findBySurveyId(survey_id).size());
        return "answer-save";
    }
}