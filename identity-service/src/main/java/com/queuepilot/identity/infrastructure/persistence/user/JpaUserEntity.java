package com.queuepilot.identity.infrastructure.persistence.user;

import com.queuepilot.identity.domain.user.User;
import jakarta.persistence.*;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "users")
public class JpaUserEntity {

    @Id
    private UUID id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(name = "password_hash", nullable = false)
    private String passwordHash;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private Instant createdAt;

    protected JpaUserEntity() {}
    private JpaUserEntity(UUID id, String email, String passwordHash, String status, Instant createdAt) {
        this.id = id;
        this.email = email;
        this.passwordHash = passwordHash;
        this.status = status;
        this.createdAt = createdAt;
    }

    public static JpaUserEntity fromDomain(User user) {
        return new JpaUserEntity(
                user.getId().getValue(),
                user.getEmail(),
                user.getPasswordHash(),
                user.getStatus().name(),
                user.getCreatedAt()
        );
    }

    public UUID getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public String getStatus() {
        return status;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }
}
