package haeun.kim.surveyproject.controller;

import haeun.kim.surveyproject.config.auth.LoginUser;
import haeun.kim.surveyproject.config.auth.dto.SessionUser;
import haeun.kim.surveyproject.dto.AnswersResponseDto;
import haeun.kim.surveyproject.dto.QuestionsResponseDto;
import haeun.kim.surveyproject.service.AnswersService;
import haeun.kim.surveyproject.service.QuestionsService;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
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
        model.addAttribute("surveyId", id);
        model.addAttribute("questionList", questionList);
        model.addAttribute("questionSize", questionList.size());
        model.addAttribute("answerList", answerList);
        model.addAttribute("answerSize", answerList.size());

        return "surveys-result";
    }

    @GetMapping("/excelDownXlsx/{id}")
    public void excelDownXlsx(@PathVariable Long id, HttpServletResponse response) throws IOException {
        List<QuestionsResponseDto> questionList = questionsService.findBySurveyId(id); //해당 설문이 가진 질문 찾기
        // 질문들이 가진 답 찾기
        List<AnswersResponseDto> answerList = answersService.findByQuestionId(questionList.get(0).getId());
        for(int i = 1; i < questionList.size(); i++)
        {
            List<AnswersResponseDto> temp = answersService.findByQuestionId(questionList.get(i).getId());
            answerList.addAll(temp);
        }

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
