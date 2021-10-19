package haeun.kim.surveyproject.controller;

import haeun.kim.surveyproject.config.auth.LoginUser;
import haeun.kim.surveyproject.config.auth.dto.SessionUser;
import haeun.kim.surveyproject.service.PostsService;
import haeun.kim.surveyproject.service.QuestionsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class QuestionsController {

    private final PostsService postsService;
    private final QuestionsService questionsService;

    @GetMapping("/questions/save/{postId}")
    public String questionsSave(Model model, @PathVariable Long postId, @LoginUser SessionUser user) {
        model.addAttribute("Post", postsService.findById(postId));
        return "questions-save";
    }

    @GetMapping("/questions/update/{id}")
    public String questionsUpdate(@PathVariable Long id, Model model) {
        model.addAttribute("id", id);
        model.addAttribute("questions", questionsService.findByPostId(id));
        model.addAttribute("questionsSize", questionsService.findByPostId(id).size());
        return "questions-update";
    }

    @GetMapping("/questions/add/{id}")
    public String questionsAdd(@PathVariable Long id, Model model, @LoginUser SessionUser user) {
        model.addAttribute("post", postsService.findById(id));
        return "questions-add";
    }
}
