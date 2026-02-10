package com.queuepilot.identity.application.user;

import com.queuepilot.identity.domain.user.User;
import com.queuepilot.identity.domain.user.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DeactivateUserService {

    private final UserRepository userRepository;
    public DeactivateUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void deactivate(DeactivateUserCommand command) {
        User user = userRepository.findById(command.userId())
                .orElseThrow(()-> new IllegalStateException("User not found"));

        user.deactivate();
        userRepository.save(user);
    }
}
