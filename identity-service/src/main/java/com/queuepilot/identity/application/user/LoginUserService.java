package com.queuepilot.identity.application.user;

import com.queuepilot.identity.application.auth.AuthToken;
import com.queuepilot.identity.application.auth.TokenProvider;
import com.queuepilot.identity.domain.user.PasswordHasher;
import com.queuepilot.identity.domain.user.User;
import com.queuepilot.identity.domain.user.UserRepository;
import com.queuepilot.identity.domain.user.UserStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(readOnly = true)
public class LoginUserService {
    private final UserRepository userRepository;
    private final TokenProvider tokenProvider;

    public LoginUserService(UserRepository userRepository, TokenProvider tokenProvider) {
        this.userRepository = userRepository;
        this.tokenProvider = tokenProvider;
    }

    public AuthToken login(LoginUserCommand command){
        User user = userRepository.findByEmail(command.email())
                .orElseThrow(()->new IllegalStateException("User with email " + command.email() + " not found"));

        if (!user.isActive()){
            throw new IllegalStateException("User with email " + command.email() + " is not active.");
        }

        if (!user.passwordMatch(command.password())){
            throw new IllegalStateException("User password does not match provided password.");
        }

        String token = tokenProvider.generate(user);
        return new AuthToken(token);

    }
}
