package com.queuepilot.identity.infrastructure.security;

import com.queuepilot.identity.application.auth.TokenValidator;
import com.queuepilot.identity.domain.user.UserId;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.UUID;

@Component
public class JwtTokenValidator implements TokenValidator {

    private final SecretKey secretKey;

    public JwtTokenValidator(SecretKey secretKey) {
        this.secretKey = secretKey;
    }

    @Override
    public UserId validateAndExtractUserId(String token) {
        Claims claims = Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseClaimsJws(token)
                .getPayload();
        String userId = claims.getSubject();

        if (userId == null) {
            throw new IllegalStateException("Invalid token");
        }

        return UserId.from(UUID.fromString(userId));
    }
}
