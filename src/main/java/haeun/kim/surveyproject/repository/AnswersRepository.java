package haeun.kim.surveyproject.repository;

import haeun.kim.surveyproject.domain.Answers;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnswersRepository extends JpaRepository<Answers, Long> {
    List<Answers> findByQuestionId(Long id);
}