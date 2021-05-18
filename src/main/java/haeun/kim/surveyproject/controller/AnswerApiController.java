package haeun.kim.surveyproject.controller;

import haeun.kim.surveyproject.config.auth.LoginUser;
import haeun.kim.surveyproject.config.auth.dto.SessionUser;
import haeun.kim.surveyproject.domain.user.Users;
import haeun.kim.surveyproject.dto.AnswersSaveRequestDto;
import haeun.kim.surveyproject.service.AnswersService;
import haeun.kim.surveyproject.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@RestController
public class AnswerApiController {

    private final AnswersService answersService;
    private final UsersService usersService;
    private final HttpSession httpSession;

    @PostMapping("/api/v1/answers")
    public Long save(@RequestBody AnswersSaveRequestDto requestDto, @LoginUser SessionUser user) {
        Users users = usersService.earnPoints(user.getEmail(), 10);
        httpSession.setAttribute("user", new SessionUser(users));
        return answersService.save(requestDto);
    }
}
