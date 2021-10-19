package haeun.kim.surveyproject.controller;

import haeun.kim.surveyproject.config.auth.LoginUser;
import haeun.kim.surveyproject.config.auth.dto.SessionUser;
import haeun.kim.surveyproject.domain.Participations;
import haeun.kim.surveyproject.domain.Posts;
import haeun.kim.surveyproject.dto.*;
import haeun.kim.surveyproject.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;
    private final QuestionsService questionsService;
    private final AnswersService answersService;
    private final ParticipationsService participationsService;

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user, @PageableDefault(size = 10) Pageable pageable) {
        Page<Posts> posts = postsService.findAllDesc(pageable);
        model.addAttribute("posts", posts);
        model.addAttribute("isNotFirst", pageable.hasPrevious());
        model.addAttribute("hasNext", posts.hasNext());
        model.addAttribute("previous", pageable.previousOrFirst().getPageNumber());
        model.addAttribute("next", pageable.next().getPageNumber());
        if (user != null) {
            model.addAttribute("user", user);
            if (user.getSubject() != null) {
                List<PostsListResponseDto> postList = postsService.findAllBySubject(user.getSubject());
                if (!postList.isEmpty()) {
					int maxSize;
					maxSize = Math.min(postList.size(), 5);
					List<PostsListResponseDto> newList = new ArrayList<>();
					for(int i = 0; i < maxSize; i++)
						newList.add(postList.get(postList.size() - i - 1));
                    model.addAttribute("subjectPosts", newList);
                }
            }
        }
        return "index";
    }

    @GetMapping("/mypage")
    public String mypage(Model model, @LoginUser SessionUser user) {
        model.addAttribute("surveys", postsService.findAllByAuthorEmail(user.getEmail()));
        model.addAttribute("user", user);
        return "mypage";
    }

    @GetMapping("/posts/save")
    public String postsSave(Model model, @LoginUser SessionUser user, @PageableDefault(size = 5) Pageable pageable) {
        if (user != null) {
            model.addAttribute("userName", user.getName());
            model.addAttribute("userEmail", user.getEmail());
            model.addAttribute("userPoint", user.getPoint());
        }
        return "posts-save";
    }

    @GetMapping("/posts/detail/{id}")
    public String postsDetail(@PathVariable Long id, Model model, @LoginUser SessionUser user) {
        PostsResponseDto dto = postsService.findById(id);
        LocalDateTime currentDate = LocalDateTime.now();
        boolean expired = dto.getExpiredDate().isAfter(currentDate);
        boolean isParticipated = false;

        // 사용자가 참여한 설문인지 확인
        List<ParticipationsResponseDto> participationsList = participationsService.findByPostId(id);
        for (ParticipationsResponseDto participationsResponseDto : participationsList) {
            if (participationsResponseDto.getUserEmail().equals(user.getEmail())) {
                isParticipated = true;
                break;
            }
        }
        model.addAttribute("isParticipated", isParticipated);

        //해당 설문이 가진 질문 찾기
        List<QuestionsResponseDto> questionList = questionsService.findByPostId(id);
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
            model.addAttribute("userPoint", user.getPoint());
        }
        return "posts-detail";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model, @LoginUser SessionUser user) {
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);
        if (user != null) {
            model.addAttribute("mySurveys", postsService.findAllByAuthorEmail(user.getEmail()));
        }
        return "posts-update";
    }
}
