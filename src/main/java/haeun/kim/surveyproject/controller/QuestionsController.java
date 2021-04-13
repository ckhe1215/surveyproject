package haeun.kim.surveyproject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class QuestionsController {

    @GetMapping("/questions/save")
    public String questionsSave() {
        return "questions-save";
    }
}
