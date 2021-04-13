package haeun.kim.surveyproject.service;

import haeun.kim.surveyproject.dto.SurveysSaveRequestDto;
import haeun.kim.surveyproject.repository.SurveysRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class SurveysService {

    private final SurveysRepository surveysRepository;

    @Transactional
    public Long save(SurveysSaveRequestDto requestDto) {
        return surveysRepository.save(requestDto.toEntity()).getId();
    }
}
