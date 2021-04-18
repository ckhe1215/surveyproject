package haeun.kim.surveyproject.controller;

import haeun.kim.surveyproject.config.auth.LoginUser;
import haeun.kim.surveyproject.config.auth.dto.SessionUser;
import haeun.kim.surveyproject.service.SurveysService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class QuestionsController {

    private final SurveysService surveysService;

    @GetMapping("/questions/save")
    public String questionsSave(Model model, @LoginUser SessionUser user) {
        model.addAttribute("Survey", surveysService.findTop1ByAuthorOrderByCreatedDateDesc(user.getEmail()));
        return "questions-save";
    }
}
