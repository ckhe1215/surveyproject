package haeun.kim.surveyproject.repository;

import haeun.kim.surveyproject.domain.Questions;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionsRepository extends JpaRepository<Questions, Long> {
    List<Questions> findByPostId(Long id);
}