package haeun.kim.surveyproject.service;

import haeun.kim.surveyproject.domain.user.Users;
import haeun.kim.surveyproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UsersService {

	private final UserRepository userRepository;

	@Transactional
	public Users earnPoints(String email, int num) {
		Users user = userRepository.findByEmail(email)
				.orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다."));
		user.earnPoints(num);
		return user;
	}

	@Transactional
	public Users updateSubject(String email, String subject) {
		Users user = userRepository.findByEmail(email)
				.orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다."));
		user.updateSubject(subject);
		return user;
	}

}
