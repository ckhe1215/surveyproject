package haeun.kim.surveyproject.controller;

import haeun.kim.surveyproject.config.auth.LoginUser;
import haeun.kim.surveyproject.config.auth.dto.SessionUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class SurveysController {

    @GetMapping("/surveys/save")
    public String surveysSave(Model model, @LoginUser SessionUser user) {
        if (user != null) {
            model.addAttribute("userEmail", user.getEmail());
        }
        return "surveys-save";
    }
}
