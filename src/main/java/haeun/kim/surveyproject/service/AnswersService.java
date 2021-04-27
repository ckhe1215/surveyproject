package haeun.kim.surveyproject.service;

import haeun.kim.surveyproject.dto.AnswersSaveRequestDto;
import haeun.kim.surveyproject.repository.AnswersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class AnswersService {

    public final AnswersRepository answersRepository;

    @Transactional
    public Long save(AnswersSaveRequestDto requestDto) {
        return answersRepository.save(requestDto.toEntity()).getId();
    }
}
