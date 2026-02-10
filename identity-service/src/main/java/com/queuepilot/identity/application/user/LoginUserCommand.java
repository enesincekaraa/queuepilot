package com.queuepilot.identity.application.user;

public record LoginUserCommand(
        String email,
        String password
) {
}
