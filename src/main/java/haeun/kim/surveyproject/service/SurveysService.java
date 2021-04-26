package haeun.kim.surveyproject.service;

import haeun.kim.surveyproject.domain.Surveys;
import haeun.kim.surveyproject.dto.SurveysListResponseDto;
import haeun.kim.surveyproject.dto.SurveysResponseDto;
import haeun.kim.surveyproject.dto.SurveysSaveRequestDto;
import haeun.kim.surveyproject.repository.SurveysRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class SurveysService {

    private final SurveysRepository surveysRepository;

    @Transactional
    public Long save(SurveysSaveRequestDto requestDto) {
        return surveysRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional(readOnly = true)
    public List<SurveysListResponseDto> findByUser(String email) {
        return surveysRepository.findByUser(email).stream()
                .map(SurveysListResponseDto::new)
                .collect(Collectors.toList());
    }

    public SurveysResponseDto findTop1ByAuthorOrderByCreatedDateDesc(String email){
        Surveys entity = surveysRepository.findTop1ByAuthorOrderByCreatedDateDesc(email);

        return new SurveysResponseDto(entity);
    }

    public SurveysResponseDto findOneById(Long id) {
        Surveys entity = surveysRepository.findOneById(id);

        return new SurveysResponseDto(entity);
    }
}
