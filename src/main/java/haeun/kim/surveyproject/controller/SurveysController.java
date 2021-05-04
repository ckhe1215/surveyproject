package haeun.kim.surveyproject.controller;

import haeun.kim.surveyproject.config.auth.LoginUser;
import haeun.kim.surveyproject.config.auth.dto.SessionUser;
import haeun.kim.surveyproject.dto.AnswersResponseDto;
import haeun.kim.surveyproject.dto.QuestionsResponseDto;
import haeun.kim.surveyproject.service.AnswersService;
import haeun.kim.surveyproject.service.QuestionsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class SurveysController {

    private final QuestionsService questionsService;
    private final AnswersService answersService;

    @GetMapping("/surveys/save")
    public String surveysSave(Model model, @LoginUser SessionUser user) {
        if (user != null) {
            model.addAttribute("userEmail", user.getEmail());
        }
        return "surveys-save";
    }

    @GetMapping("/surveys/result/{id}")
    public String surveysResult(@PathVariable Long id, Model model) {
        List<QuestionsResponseDto> questionList = questionsService.findBySurveyId(id); //해당 설문이 가진 질문 찾기
        // 질문들이 가진 답 찾기
        List<AnswersResponseDto> answerList = answersService.findByQuestionId(questionList.get(0).getId());
        for(int i = 1; i < questionList.size(); i++)
        {
            List<AnswersResponseDto> temp = answersService.findByQuestionId(questionList.get(i).getId());
            answerList.addAll(temp);
        }
        // 답들을 모델에 저장하기
        model.addAttribute("questionList", questionList);
        model.addAttribute("questionSize", questionList.size());
        model.addAttribute("answerList", answerList);
        model.addAttribute("answerSize", answerList.size());

        return "surveys-result";
    }
}
