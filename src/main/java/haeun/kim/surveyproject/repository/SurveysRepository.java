package haeun.kim.surveyproject.repository;

import haeun.kim.surveyproject.domain.Surveys;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SurveysRepository extends JpaRepository<Surveys, Long> {
    @Query("SELECT s FROM Surveys s WHERE s.author=:email")
    List<Surveys> findByUser(@Param("email") String email);
    Surveys findTop1ByAuthorOrderByCreatedDateDesc(String email);
    List<Surveys> findAllBySubject(String subject);
}
