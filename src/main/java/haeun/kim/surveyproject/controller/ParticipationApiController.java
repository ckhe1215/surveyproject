package haeun.kim.surveyproject.controller;

import haeun.kim.surveyproject.dto.ParticipationsSaveRequestDto;
import haeun.kim.surveyproject.service.ParticipationsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class ParticipationApiController {

	private final ParticipationsService participationsService;

	@PostMapping("api/v1/participations")
	public Long save(@RequestBody ParticipationsSaveRequestDto requestDto){
		return participationsService.save(requestDto);
	}
}
