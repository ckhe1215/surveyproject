package haeun.kim.surveyproject.controller;

import haeun.kim.surveyproject.config.auth.LoginUser;
import haeun.kim.surveyproject.config.auth.dto.SessionUser;
import haeun.kim.surveyproject.domain.Participations;
import haeun.kim.surveyproject.domain.Posts;
import haeun.kim.surveyproject.dto.*;
import haeun.kim.surveyproject.service.*;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
        int answerCnt = 0;
        if (questionList.size() > 0) {
            List<AnswersResponseDto> answerList = answersService.findByQuestionId(questionList.get(0).getId());
            for(int i = 1; i < questionList.size(); i++)
            {
                List<AnswersResponseDto> temp = answersService.findByQuestionId(questionList.get(i).getId());
                answerList.addAll(temp);
            }
            //답변 갯수 찾기
            answerCnt =  answerList.size() / questionList.size();
        }
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

    @GetMapping("/posts/result/{id}")
    public String surveysResult(@PathVariable Long id, Model model) {
        List<QuestionsResponseDto> questionList = questionsService.findByPostId(id); //해당 설문이 가진 질문 찾기
        // 질문들이 가진 답 찾기
        List<AnswersResponseDto> answerList = new ArrayList<>();
        for (QuestionsResponseDto questionsResponseDto : questionList)
            answerList.addAll(answersService.findByQuestionId(questionsResponseDto.getId()));
        // 답들을 모델에 저장하기
        model.addAttribute("surveyId", id);
        model.addAttribute("questionList", questionList);
        model.addAttribute("questionSize", questionList.size());
        model.addAttribute("answerList", answerList);
        model.addAttribute("answerSize", answerList.size());

        return "surveys-result";
    }

    @GetMapping("/excelDownXlsx/{id}")
    public void excelDownXlsx(@PathVariable Long id, HttpServletResponse response) throws IOException {
        List<QuestionsResponseDto> questionList = questionsService.findByPostId(id); //해당 설문이 가진 질문 찾기
        // 질문들이 가진 답 찾기
        List<AnswersResponseDto> answerList = new ArrayList<>();
        for (QuestionsResponseDto questionsResponseDto : questionList)
            answerList.addAll(answersService.findByQuestionId(questionsResponseDto.getId()));

        Workbook wb = new XSSFWorkbook();
        Sheet sheet = wb.createSheet("결과");
        Row row = null;
        Cell cell = null;
        int rowNum = 0;

        // Header
        row = sheet.createRow(rowNum++);
        cell = row.createCell(0);
        cell.setCellValue("질문ID");
        cell = row.createCell(1);
        cell.setCellValue("answer1");
        cell = row.createCell(2);
        cell.setCellValue("answer2");
        cell = row.createCell(3);
        cell.setCellValue("answer3");
        cell = row.createCell(4);
        cell.setCellValue("answer4");
        cell = row.createCell(5);
        cell.setCellValue("answer5");
        cell = row.createCell(6);
        cell.setCellValue("answer6");
        cell = row.createCell(7);
        cell.setCellValue("answer7");
        cell = row.createCell(8);
        cell.setCellValue("answer8");
        cell = row.createCell(9);
        cell.setCellValue("answer9");
        cell = row.createCell(10);
        cell.setCellValue("answer10");
        cell = row.createCell(11);
        cell.setCellValue("주관식 답");

        // Body
        for (AnswersResponseDto answersResponseDto : answerList) {
            row = sheet.createRow(rowNum++);
            cell = row.createCell(0);
            cell.setCellValue(answersResponseDto.getQuestionId());
            cell = row.createCell(1);
            cell.setCellValue(answersResponseDto.getAnswer1());
            cell = row.createCell(2);
            cell.setCellValue(answersResponseDto.getAnswer2());
            cell = row.createCell(3);
            cell.setCellValue(answersResponseDto.getAnswer3());
            cell = row.createCell(4);
            cell.setCellValue(answersResponseDto.getAnswer4());
            cell = row.createCell(5);
            cell.setCellValue(answersResponseDto.getAnswer5());
            cell = row.createCell(6);
            cell.setCellValue(answersResponseDto.getAnswer6());
            cell = row.createCell(7);
            cell.setCellValue(answersResponseDto.getAnswer7());
            cell = row.createCell(8);
            cell.setCellValue(answersResponseDto.getAnswer8());
            cell = row.createCell(9);
            cell.setCellValue(answersResponseDto.getAnswer9());
            cell = row.createCell(10);
            cell.setCellValue(answersResponseDto.getAnswer10());
            cell = row.createCell(11);
            cell.setCellValue(answersResponseDto.getEssayAnswer());
        }

        // 컨텐츠 타입과 파일명 지정
        response.setContentType("ms-vnd/excel");
        response.setHeader("Content-Disposition", "attachment;filename=result.xlsx");

        // Excel File Output
        wb.write(response.getOutputStream());
        wb.close();
    }
}
