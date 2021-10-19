package haeun.kim.surveyproject.dto;

import haeun.kim.surveyproject.domain.Participations;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ParticipationsSaveRequestDto {
	private String userEmail;
	private Long postId;

	@Builder
	public ParticipationsSaveRequestDto(String userEmail, Long postId){
		this.userEmail = userEmail;
		this.postId = postId;
	}
	
	public Participations toEntity(){
		return Participations.builder()
				.userEmail(userEmail)
				.postId(postId)
				.build();
	}
}
