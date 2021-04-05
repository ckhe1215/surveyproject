package haeun.kim.surveyproject.repository;

import haeun.kim.surveyproject.domain.Posts;
import haeun.kim.surveyproject.dto.PostsSaveRequestDto;
import haeun.kim.surveyproject.dto.PostsUpdateRequestDto;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PostApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private PostsRepository postsRepository;

    @After
    public void tearDown() throws Exception {
        postsRepository.deleteAll();
    }

    @Test
    public void Posts_등록된다() throws Exception {
        // given
        String title = "title";
        String content = "content";
        String author = "test";
        Long surveyId = 12345L;
        int answerGoal = 200;
        LocalDateTime expiredDate = LocalDateTime.of(2020, 4, 4, 17, 54);
        boolean isExpired = false;

        PostsSaveRequestDto requestDto = PostsSaveRequestDto.builder()
                .title(title)
                .content(content)
                .author(author)
                .surveyId(surveyId)
                .answerGoal(answerGoal)
                .expiredDate(expiredDate)
                .isExpired(isExpired)
                .build();

        String url = "http://localhost:" + port + "/api/v1/posts";

        // when
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, requestDto, Long.class);

        // then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Posts> all = postsRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(title);
        assertThat(all.get(0).getContent()).isEqualTo(content);
        assertThat(all.get(0).getAuthor()).isEqualTo(author);
        assertThat(all.get(0).getSurveyId()).isEqualTo(surveyId);
        assertThat(all.get(0).getAnswerGoal()).isEqualTo(answerGoal);
        assertThat(all.get(0).getExpiredDate()).isEqualTo(expiredDate);
        assertThat(all.get(0).isExpired()).isEqualTo(isExpired);
    }

    @Test
    public void Posts_수정된다() throws Exception {
        // given
        Posts savedPosts = postsRepository.save(Posts.builder()
                .title("title")
                .content("content")
                .author("author")
                .surveyId(12345L)
                .answerGoal(200)
                .expiredDate(LocalDateTime.of(2020, 4, 4, 17, 54))
                .isExpired(false)
                .build());

        Long updatedId = savedPosts.getId();
        String expectedTitle = "title2";
        String expectedContent = "content2";
        Long expectedSurveyId = 123456L;
        int expectedAnswerGoal = 500;
        LocalDateTime expectedExpiredDate = LocalDateTime.of(2020, 5, 4, 17, 54);
        boolean expectedIsExpired = true;

        PostsUpdateRequestDto requestDto = PostsUpdateRequestDto.builder()
                .title(expectedTitle)
                .content(expectedContent)
                .surveyId(expectedSurveyId)
                .answerGoal(expectedAnswerGoal)
                .expiredDate(expectedExpiredDate)
                .isExpired(expectedIsExpired)
                .build();

        String url = "http://localhost:" + port + "/api/v1/posts/" + updatedId;

        HttpEntity<PostsUpdateRequestDto> requestEntity = new HttpEntity<>(requestDto);

        // when
        ResponseEntity<Long> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Long.class);

        // then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Posts> all = postsRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(expectedTitle);
        assertThat(all.get(0).getContent()).isEqualTo(expectedContent);
        assertThat(all.get(0).getSurveyId()).isEqualTo(expectedSurveyId);
        assertThat(all.get(0).getAnswerGoal()).isEqualTo(expectedAnswerGoal);
        assertThat(all.get(0).getExpiredDate()).isEqualTo(expectedExpiredDate);
        // assertThat(all.get(0).isExpired()).isEqualTo(expectedIsExpired);
    }
}
