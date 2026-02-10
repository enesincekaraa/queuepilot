package com.queuepilot.identity.presentation;

import com.queuepilot.identity.application.auth.TokenValidator;
import com.queuepilot.identity.infrastructure.security.JwtTokenValidator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/internal/token")
public class TokenTestController {
    private final TokenValidator tokenValidator;

    public TokenTestController(TokenValidator tokenValidator) {
        this.tokenValidator = tokenValidator;
    }


    @PostMapping("/validate")
    public String validate(@RequestHeader("Authorization") String header) {

        String token = header.startsWith("Bearer ") ? header.substring(7) : header;
        var userId = tokenValidator.validateAndExtractUserId(token);

        return "VALID TOKEN. USER ID = " + userId.getValue();
    }
}
