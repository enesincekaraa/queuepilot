package com.queuepilot.identity.application.user;

public record RegisterUserCommand(
        String email,
        String password
) {
}
