package haeun.kim.surveyproject.repository;

import haeun.kim.surveyproject.domain.Surveys;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SurveysRepository extends JpaRepository<Surveys, Long> {
}
