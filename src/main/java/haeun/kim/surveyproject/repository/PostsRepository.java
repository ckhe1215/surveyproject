package haeun.kim.surveyproject.repository;

import haeun.kim.surveyproject.domain.Posts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostsRepository extends JpaRepository<Posts, Long> {
}
