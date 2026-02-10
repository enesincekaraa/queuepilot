package com.queuepilot.identity.application.user;

import com.queuepilot.identity.domain.user.UserId;

public record DeactivateUserCommand(
        UserId userId
) {
}
