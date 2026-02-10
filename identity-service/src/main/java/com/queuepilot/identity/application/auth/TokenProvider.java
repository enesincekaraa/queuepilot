package com.queuepilot.identity.application.auth;

import com.queuepilot.identity.domain.user.User;

public interface TokenProvider {
    String generate(User user);
}
