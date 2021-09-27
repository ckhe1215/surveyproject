package haeun.kim.surveyproject.service;

import haeun.kim.surveyproject.domain.Posts;
import haeun.kim.surveyproject.dto.PostsListResponseDto;
import haeun.kim.surveyproject.dto.PostsResponseDto;
import haeun.kim.surveyproject.dto.PostsSaveRequestDto;
import haeun.kim.surveyproject.dto.PostsUpdateRequestDto;
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

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+ id));

        posts.update(requestDto.getTitle(), requestDto.getContent(), requestDto.getSurveyId(), requestDto.getAnswerGoal(), requestDto.getExpiredDate());
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

    public Page<Posts> findAllByAuthorEmail(String email, Pageable pageable) {
        return postsRepository.findAllByAuthorEmail(email, pageable);
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

    public  PostsResponseDto findTop1ByAuthorOrderByCreatedDateDesc(String email) {
        Posts entity = postsRepository.findTop1ByAuthorOrderByCreatedDateDesc(email);

        return new PostsResponseDto(entity);
    }

    @Transactional
    public void delete (Long id) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        postsRepository.delete(posts);
    }
}
