package com.queuepilot.identity.presentation.user;

import com.queuepilot.identity.application.user.DeactivateUserCommand;
import com.queuepilot.identity.application.user.DeactivateUserService;
import com.queuepilot.identity.application.user.RegisterUserCommand;
import com.queuepilot.identity.application.user.RegisterUserService;
import com.queuepilot.identity.domain.user.UserId;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/identity/users")
public class UserController {
    private final RegisterUserService registerUserService;
    private final DeactivateUserService deactivateUserService;

    public UserController(RegisterUserService registerUserService, DeactivateUserService deactivateUserService) {
        this.registerUserService = registerUserService;
        this.deactivateUserService = deactivateUserService;
    }

    @PostMapping
    public ResponseEntity<String> register(
            @Valid @RequestBody RegisterUserRequest req) {
        RegisterUserCommand command = new RegisterUserCommand(req.email(), req.password());
        UserId userId = registerUserService.register(command);
        return ResponseEntity.status(HttpStatus.CREATED).body(userId.getValue().toString());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        DeactivateUserCommand command = new DeactivateUserCommand(new UserId(id));

        deactivateUserService.deactivate(command);
        return ResponseEntity.noContent().build();

    }
}
