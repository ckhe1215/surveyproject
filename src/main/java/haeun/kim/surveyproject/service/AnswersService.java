package haeun.kim.surveyproject.service;

import haeun.kim.surveyproject.dto.AnswersResponseDto;
import haeun.kim.surveyproject.dto.AnswersSaveRequestDto;
import haeun.kim.surveyproject.dto.QuestionsResponseDto;
import haeun.kim.surveyproject.repository.AnswersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AnswersService {

    public final AnswersRepository answersRepository;

    @Transactional
    public Long save(AnswersSaveRequestDto requestDto) {
        return answersRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public List<AnswersResponseDto> findByQuestionId(Long id){
        return answersRepository.findByQuestionId(id).stream()
                .map(AnswersResponseDto::new)
                .collect(Collectors.toList());
    }
}
