package com.queuepilot.identity.domain.user;

import jakarta.persistence.*;

import java.time.Instant;
import java.util.Objects;


public class User {

    @EmbeddedId
    private UserId id;

    @Column(nullable = false,unique = true)
    private String email;
    @Column(nullable = false)
    private String passwordHash;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserStatus status;

    @Column(nullable = false, updatable = false)
    private Instant createdAt;

    protected User() {}

    private User(UserId id, String email, String passwordHash, UserStatus status, Instant createdAt) {

        this.id = id;
        this.email = Objects.requireNonNull(email);
        this.passwordHash = Objects.requireNonNull(passwordHash);
        this.status = Objects.requireNonNull(status);
        this.createdAt = createdAt;

    }


    public static User register(String email,String rawPassword){
        return new User(
                UserId.generate(),
                email,
                PasswordHasher.hash(rawPassword),
                UserStatus.ACTIVE,
                Instant.now()
        );

    }

    public static User rehydrate(
            UserId id,
            String email,
            String passwordHash,
            UserStatus status,
            Instant createdAt
    ) {
        return new User(id, email, passwordHash, status, createdAt);
    }

    public void deactivate(){
        if (this.status == UserStatus.PASSIVE){
            throw  new IllegalStateException("User already passive");
        }
        this.status = UserStatus.PASSIVE;
    }

    public boolean isActive(){
        return this.status == UserStatus.ACTIVE;
    }

    public boolean passwordMatch(String rawPassword){
        return this.passwordHash.equals(PasswordHasher.hash(rawPassword));
    }


    public UserId getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public UserStatus getStatus() {
        return status;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }
}
