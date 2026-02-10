package com.queuepilot.identity.application.user;

import com.queuepilot.identity.domain.user.User;
import com.queuepilot.identity.domain.user.UserId;
import com.queuepilot.identity.domain.user.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RegisterUserService {
    private final UserRepository userRepository;

    public RegisterUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserId register(RegisterUserCommand command) {

        if (userRepository.existsByEmail(command.email())){
            throw new IllegalArgumentException("Email already exists");
        }

        User user = User.register(
                command.email(),
                command.password()
        );

        userRepository.save(user);
        return user.getId();
    }
}
