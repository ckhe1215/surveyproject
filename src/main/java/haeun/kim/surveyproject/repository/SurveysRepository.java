package haeun.kim.surveyproject.repository;

import haeun.kim.surveyproject.domain.Surveys;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SurveysRepository extends JpaRepository<Surveys, Long> {
    @Query("SELECT s FROM Surveys s ORDER BY s.author DESC")
    List<Surveys> findByUser(String email);
}
