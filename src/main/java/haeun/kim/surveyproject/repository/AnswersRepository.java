package haeun.kim.surveyproject.repository;

import haeun.kim.surveyproject.domain.Answers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswersRepository extends JpaRepository<Answers, Long> {
}