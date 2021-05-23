package haeun.kim.surveyproject.controller;

import haeun.kim.surveyproject.config.auth.LoginUser;
import haeun.kim.surveyproject.config.auth.dto.SessionUser;
import haeun.kim.surveyproject.domain.user.Users;
import haeun.kim.surveyproject.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@RestController
public class UsersApiController {

	private final UsersService usersService;
	private final HttpSession httpSession;

	@PostMapping("/api/v1/users/subject")
	public void updateSubject(@RequestBody String subject, @LoginUser SessionUser user) {
		Users users = usersService.updateSubject(user.getEmail(),subject);
		httpSession.setAttribute("user", new SessionUser(users));
	}
}
