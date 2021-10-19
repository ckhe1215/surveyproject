package haeun.kim.surveyproject.service;

import haeun.kim.surveyproject.dto.ParticipationsResponseDto;
import haeun.kim.surveyproject.dto.ParticipationsSaveRequestDto;
import haeun.kim.surveyproject.repository.ParticipationsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ParticipationsService {

	private final ParticipationsRepository participationsRepository;

	@Transactional
	public Long save(ParticipationsSaveRequestDto requestDto) {
		return participationsRepository.save(requestDto.toEntity()).getId();
	}

	@Transactional
	public List<ParticipationsResponseDto> findByPostId(Long id){
		return participationsRepository.findByPostId(id).stream()
				.map(ParticipationsResponseDto::new)
				.collect(Collectors.toList());
	}
}
