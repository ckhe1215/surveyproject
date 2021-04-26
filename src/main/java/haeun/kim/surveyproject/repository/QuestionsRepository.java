package haeun.kim.surveyproject.repository;

import haeun.kim.surveyproject.domain.Questions;
import haeun.kim.surveyproject.domain.Surveys;
import haeun.kim.surveyproject.dto.QuestionsResponseDto;
import haeun.kim.surveyproject.dto.SurveysResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QuestionsRepository extends JpaRepository<Questions, Long> {
    List<Questions> findBySurveyId(Long id);
}