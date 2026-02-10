package com.queuepilot.identity.domain.user;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.util.Objects;
import java.util.UUID;

@Embeddable
public class UserId {

    @Column(name = "id", nullable = false)
    private UUID value;

    protected UserId() {
    }

    public UserId(UUID value) {
        this.value = Objects.requireNonNull(value);
    }

    public static UserId generate() {
        return new UserId(UUID.randomUUID());
    }

    public static UserId from(UUID value) {
        return new UserId(value);
    }

    public UUID getValue() {
        return value;
    }
}
