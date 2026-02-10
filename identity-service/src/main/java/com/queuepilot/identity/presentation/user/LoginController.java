package com.queuepilot.identity.presentation.user;

import com.queuepilot.identity.application.auth.AuthToken;
import com.queuepilot.identity.application.user.LoginUserCommand;
import com.queuepilot.identity.application.user.LoginUserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/identity/auth")
public class LoginController {
    private final LoginUserService loginUserService;


    public LoginController(LoginUserService loginUserService) {
        this.loginUserService = loginUserService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthToken> login(@Valid @RequestBody LoginRequest req) {
        LoginUserCommand command = new LoginUserCommand(req.email(),  req.password());

        AuthToken token = loginUserService.login(command);
        return ResponseEntity.ok(token);
    }
}
