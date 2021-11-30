package haeun.kim.surveyproject.service;

import haeun.kim.surveyproject.domain.Posts;
import haeun.kim.surveyproject.dto.*;
import haeun.kim.surveyproject.repository.PostsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostsRepository postsRepository;
    private final QuestionsService questionsService;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+ id));

        posts.update(requestDto.getTitle(), requestDto.getContent(), requestDto.getAnswerGoal(), requestDto.getExpiredDate());
        return id;
    }

    public PostsResponseDto findById (Long id) {
        Posts entity = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        return new PostsResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public Page<Posts> findAllDesc(Pageable pageable) {
        return postsRepository.findAllDesc(pageable);
    }

    public List<PostsListResponseDto> findAllByAuthorEmail(String email){
        return postsRepository.findAllByAuthorEmail(email).stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }

    public List<PostsListResponseDto> findAllBySubject(String subject){
        return postsRepository.findAllBySubject(subject).stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete (Long id) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        List<QuestionsResponseDto> questions = questionsService.findByPostId(id);
        for (QuestionsResponseDto question : questions) {
            questionsService.delete(question.getId());
        }
        postsRepository.delete(posts);
    }
}
