package com.queuepilot.identity.infrastructure.persistence.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface SpringDataUserJpaRepository extends JpaRepository<JpaUserEntity, UUID> {

    Optional<JpaUserEntity> findByEmail(String email);
    boolean existsByEmail(String email);
}
