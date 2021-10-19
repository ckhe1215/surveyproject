package haeun.kim.surveyproject.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Participations extends BaseTimeEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String userEmail;

	@Column(nullable = false)
	private Long postId;

	@Builder
	public Participations(String userEmail, Long postId){
		this.userEmail = userEmail;
		this.postId = postId;
	}
}
