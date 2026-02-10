package com.queuepilot.identity.infrastructure.security;

import com.queuepilot.identity.application.auth.TokenProvider;
import com.queuepilot.identity.domain.user.User;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.Date;

@Component
public class JwtTokenProvider implements TokenProvider {

    private final SecretKey secretKey;
    private final long expirationSeconds;

    public JwtTokenProvider(
            SecretKey secretKey,
            @Value("${security.jwt.expiration}") long expirationSeconds
    ) {
        this.secretKey = secretKey;
        this.expirationSeconds = expirationSeconds;
    }


    @Override
    public String generate(User user) {
        Instant now = Instant.now();

        return Jwts.builder()
                .subject(user.getId().getValue().toString())
                .claim("email",user.getEmail())
                .claim("status",user.getStatus().name())
                .issuedAt(Date.from(now))
                .expiration(Date.from(now.plusSeconds(expirationSeconds)))
                .signWith(secretKey)
                .compact();
    }
}
