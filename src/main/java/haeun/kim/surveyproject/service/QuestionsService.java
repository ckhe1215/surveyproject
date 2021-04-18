package haeun.kim.surveyproject.service;

import haeun.kim.surveyproject.dto.QuestionsSaveRequestDto;
import haeun.kim.surveyproject.repository.QuestionsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class QuestionsService {

    public final QuestionsRepository questionsRepository;

    @Transactional
    public Long save(QuestionsSaveRequestDto requestDto) {
        return questionsRepository.save(requestDto.toEntity()).getId();
    }
}
