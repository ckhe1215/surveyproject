package haeun.kim.surveyproject.repository;

import haeun.kim.surveyproject.domain.Posts;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @After
    public void cleanUp() {
        postsRepository.deleteAll();
    }

    @Test
    public void 게시글저장_불러오기() {
        // given
        String title = "게시글 제목";
        String content = "게시글 본문";
        String author = "test";
        Long surveyId = 12345L;
        int answerGoal = 200;
        LocalDateTime expiredDate = LocalDateTime.of(2020, 4, 4, 17, 54);
        boolean isExpired = false;

        postsRepository.save(Posts.builder()
            .title(title)
            .content(content)
            .author(author)
            .surveyId(surveyId)
            .answerGoal(answerGoal)
            .expiredDate(expiredDate)
            .isExpired(isExpired)
            .build());

        // when
        List<Posts> postsLists = postsRepository.findAll();

        // then
        Posts posts = postsLists.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
        assertThat(posts.getAuthor()).isEqualTo(author);
        assertThat(posts.getSurveyId()).isEqualTo(surveyId);
        assertThat(posts.getAnswerGoal()).isEqualTo(answerGoal);
        assertThat(posts.getExpiredDate()).isEqualTo(expiredDate);
        assertThat(posts.isExpired()).isEqualTo(isExpired);
    }

    @Test
    public void BaseTimeEntity_등록() {
        // given
        LocalDateTime now = LocalDateTime.of(2019,6,4,0,0,0);
        postsRepository.save(Posts.builder()
                .title("title")
                .content("content")
                .author("author")
                .surveyId(12345L)
                .answerGoal(200)
                .expiredDate(LocalDateTime.of(2020, 4, 4, 17, 54))
                .isExpired(false)
                .build());

        // when
        List<Posts> postsList = postsRepository.findAll();

        // then
        Posts posts = postsList.get(0);

        System.out.println(">>>>>>>> createdDate="+posts.getCreatedDate()+", modifiedDate="+posts.getModifiedDate());

        assertThat(posts.getCreatedDate()).isAfter(now);
        assertThat(posts.getModifiedDate()).isAfter(now);
    }
}
