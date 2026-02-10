package com.queuepilot.identity.application.auth;

import com.queuepilot.identity.domain.user.UserId;

public interface TokenValidator {

    UserId validateAndExtractUserId(String token);
}
