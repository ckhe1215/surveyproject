package haeun.kim.surveyproject.controller;

import haeun.kim.surveyproject.config.auth.LoginUser;
import haeun.kim.surveyproject.config.auth.dto.SessionUser;
import haeun.kim.surveyproject.domain.user.Users;
import haeun.kim.surveyproject.dto.PostsResponseDto;
import haeun.kim.surveyproject.dto.PostsSaveRequestDto;
import haeun.kim.surveyproject.dto.PostsUpdateRequestDto;
import haeun.kim.surveyproject.service.PostsService;
import haeun.kim.surveyproject.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@RestController
public class PostsApiController {

    private final PostsService postsService;
    private final UsersService usersService;
    private final HttpSession httpSession;

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto, @LoginUser SessionUser user) {
        Users users = usersService.earnPoints(user.getEmail(), -10);
        httpSession.setAttribute("user", new SessionUser(users));
        return postsService.save(requestDto);
    }

    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto) {
        return postsService.update(id, requestDto);
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById(@PathVariable Long id) {
        return postsService.findById(id);
    }

    @DeleteMapping("/api/v1/posts/{id}")
    public Long delete(@PathVariable Long id) {
        postsService.delete(id);
        return id;
    }
}
