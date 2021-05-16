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
	public void earnPoints(String email, int num) {
		Optional<Users> user = userRepository.findByEmail(email);
		user.map(entity -> entity.earnPoints(num));
	}

}
