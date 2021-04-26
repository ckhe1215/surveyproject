package haeun.kim.surveyproject.controller;

import haeun.kim.surveyproject.domain.Surveys;
import haeun.kim.surveyproject.dto.QuestionsResponseDto;
import haeun.kim.surveyproject.dto.SurveysResponseDto;
import haeun.kim.surveyproject.service.QuestionsService;
import haeun.kim.surveyproject.service.SurveysService;
import lombok.RequiredArgsConstructor;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.awt.*;
import java.text.ParseException;

@RequiredArgsConstructor
@Controller
public class AnswersController {

    private final QuestionsService questionsService;
    private final SurveysService surveysService;

    @Autowired
    private ModelMapper modelMapper;

//    private Surveys convertToEntity(SurveysResponseDto dto){
//        Surveys surveys = modelMapper.map(dto, Surveys.class);
//        System.out.println(surveys.getId());
//        return surveys;
//    }

    @GetMapping("/answers/save/{id}")
    public String answersSave(@PathVariable Long id, Model model){
        //SurveysResponseDto dto = surveysService.findOneById(id);
        //Surveys surveys = convertToEntity(dto.toEntity());
        //System.out.println(surveys.getId());
        //model.addAttribute("questions", surveys);
        //model.addAttribute("questions", questionsService.findBySurveys(dto.toEntity()));
        model.addAttribute("questions", questionsService.findAll());
        return "answer-save";
    }
}
