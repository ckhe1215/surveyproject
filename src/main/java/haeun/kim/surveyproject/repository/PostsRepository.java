package haeun.kim.surveyproject.repository;

import haeun.kim.surveyproject.domain.Posts;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostsRepository extends JpaRepository<Posts, Long> {
    @Query("SELECT p FROM Posts p ORDER BY p.id DESC")
    Page<Posts> findAllDesc(Pageable pageable);
    List<Posts> findAllByAuthorEmail(String email);
    List<Posts> findAllBySubject(String subject);
}
