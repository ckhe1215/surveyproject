package haeun.kim.surveyproject.repository;

import haeun.kim.surveyproject.domain.Participations;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParticipationsRepository extends JpaRepository<Participations, Long> {
	List<Participations> findBySurveyId(Long id);
}
