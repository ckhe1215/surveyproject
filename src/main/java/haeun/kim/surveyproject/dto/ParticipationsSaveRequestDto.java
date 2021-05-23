package haeun.kim.surveyproject.dto;

import haeun.kim.surveyproject.domain.Participations;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ParticipationsSaveRequestDto {
	private String userEmail;
	private Long surveyId;

	@Builder
	public ParticipationsSaveRequestDto(String userEmail, Long surveyId){
		this.userEmail = userEmail;
		this.surveyId = surveyId;
	}
	
	public Participations toEntity(){
		return Participations.builder()
				.userEmail(userEmail)
				.surveyId(surveyId)
				.build();
	}
}
