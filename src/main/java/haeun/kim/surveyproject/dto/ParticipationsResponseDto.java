package haeun.kim.surveyproject.dto;

import haeun.kim.surveyproject.domain.Participations;
import lombok.Getter;

@Getter
public class ParticipationsResponseDto {

	private Long id;
	private String userEmail;
	private Long postId;

	public ParticipationsResponseDto(Participations entity){
		this.id = entity.getId();
		this.userEmail = entity.getUserEmail();
		this.postId = entity.getPostId();
	}

}
