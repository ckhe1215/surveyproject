package haeun.kim.surveyproject.controller;

import haeun.kim.surveyproject.config.auth.LoginUser;
import haeun.kim.surveyproject.config.auth.dto.SessionUser;
import haeun.kim.surveyproject.dto.QuestionsResponseDto;
import haeun.kim.surveyproject.service.QuestionsService;
import haeun.kim.surveyproject.service.SurveysService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


@RequiredArgsConstructor
@Controller
public class QuestionsController {

    private final SurveysService surveysService;
    private final QuestionsService questionsService;

    @GetMapping("/questions/save")
    public String questionsSave(Model model, @LoginUser SessionUser user) {
        model.addAttribute("Survey", surveysService.findTop1ByAuthorOrderByCreatedDateDesc(user.getEmail()));
        return "questions-save";
    }

    @GetMapping("/questions/update/{id}")
    public String questionsUpdate(@PathVariable Long id, Model model) {
        model.addAttribute("surveyId", id);
        model.addAttribute("questions", questionsService.findBySurveyId(id));
        model.addAttribute("questionsSize", questionsService.findBySurveyId(id).size());
        return "questions-update";
    }

    @GetMapping("/questions/add/{id}")
    public String questionsAdd(@PathVariable Long id, Model model, @LoginUser SessionUser user) {
        model.addAttribute("Survey", surveysService.findById(id));
        return "questions-add";
    }
}
