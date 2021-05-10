package haeun.kim.surveyproject.service;

import haeun.kim.surveyproject.domain.Questions;
import haeun.kim.surveyproject.dto.QuestionsResponseDto;
import haeun.kim.surveyproject.dto.QuestionsSaveRequestDto;
import haeun.kim.surveyproject.repository.QuestionsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class  QuestionsService {

    public final QuestionsRepository questionsRepository;

    @Transactional
    public Long save(QuestionsSaveRequestDto requestDto) {
        return questionsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public void delete (Long id) {
        Questions questions = questionsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        questionsRepository.delete(questions);
    }

    @Transactional
    public List<QuestionsResponseDto> findBySurveyId(Long id){
        return questionsRepository.findBySurveyId(id).stream()
                .map(QuestionsResponseDto::new)
                .collect(Collectors.toList());
    }
}
