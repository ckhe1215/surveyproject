package haeun.kim.surveyproject.controller;

import haeun.kim.surveyproject.config.auth.LoginUser;
import haeun.kim.surveyproject.config.auth.dto.SessionUser;
import haeun.kim.surveyproject.dto.AnswersResponseDto;
import haeun.kim.surveyproject.dto.PostsResponseDto;
import haeun.kim.surveyproject.dto.QuestionsResponseDto;
import haeun.kim.surveyproject.service.AnswersService;
import haeun.kim.surveyproject.service.PostsService;
import haeun.kim.surveyproject.service.QuestionsService;
import haeun.kim.surveyproject.service.SurveysService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;
    private final SurveysService surveysService;
    private final QuestionsService questionsService;
    private final AnswersService answersService;

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user) {
        model.addAttribute("posts", postsService.findAllDesc());
        if (user != null) {
            model.addAttribute("userName", user.getName());
        }
        return "index";
    }

    @GetMapping("/mypage")
    public String mypage(Model model, @LoginUser SessionUser user) {
        model.addAttribute("surveys", surveysService.findByUser(user.getEmail()));
        model.addAttribute("user", user);
        return "mypage";
    }

    @GetMapping("/posts/save")
    public String postsSave(Model model, @LoginUser SessionUser user) {
        if (user != null) {
            model.addAttribute("userName", user.getName());
            model.addAttribute("userEmail", user.getEmail());
            model.addAttribute("userPoint", user.getPoint());
            model.addAttribute("mySurveys", surveysService.findByUser(user.getEmail()));
        }
        return "posts-save";
    }

    @GetMapping("/posts/detail/{id}") // 이 id는 설문 아이디가 아니라 게시글 아이디임 여기 수정해야됨
    public String postsDetail(@PathVariable Long id, Model model, @LoginUser SessionUser user) {
        PostsResponseDto dto = postsService.findById(id);
        LocalDateTime currentDate = LocalDateTime.now();
        boolean expired = dto.getExpiredDate().isAfter(currentDate);
        Long survey_id = dto.getSurveyId();
        //해당 설문이 가진 질문 찾기
        List<QuestionsResponseDto> questionList = questionsService.findBySurveyId(survey_id);
        // 질문들이 가진 답 찾기
        List<AnswersResponseDto> answerList = answersService.findByQuestionId(questionList.get(0).getId());
        for(int i = 1; i < questionList.size(); i++)
        {
            List<AnswersResponseDto> temp = answersService.findByQuestionId(questionList.get(i).getId());
            answerList.addAll(temp);
        }
        //답변 갯수 찾기
        int answerCnt =  answerList.size() / questionList.size();

        if (expired && answerCnt >= dto.getAnswerGoal())
            expired = false;
        model.addAttribute("post", dto);
        model.addAttribute("expired", expired);
        if (user != null) {
            model.addAttribute("userEmail", user.getEmail());
        }
        return "posts-detail";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model, @LoginUser SessionUser user) {
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);
        if (user != null) {
            model.addAttribute("mySurveys", surveysService.findByUser(user.getEmail()));
        }
        return "posts-update";
    }
}
