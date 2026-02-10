package com.queuepilot.identity.infrastructure.persistence.user;

import com.queuepilot.identity.domain.user.User;
import com.queuepilot.identity.domain.user.UserId;
import com.queuepilot.identity.domain.user.UserRepository;
import com.queuepilot.identity.domain.user.UserStatus;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRepositoryAdapter implements UserRepository {

    private final SpringDataUserJpaRepository jpaRepository;

    public UserRepositoryAdapter(SpringDataUserJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public void save(User user) {

        JpaUserEntity entity = JpaUserEntity.fromDomain(user);
        jpaRepository.save(entity);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return jpaRepository.findByEmail(email).map(this::toDomain);
    }

    @Override
    public boolean existsByEmail(String email) {
        return jpaRepository.existsByEmail(email);
    }

    @Override
    public Optional<User> findById(UserId id) {
        return jpaRepository.findById(id.getValue()).map(this::toDomain);
    }


    private User toDomain(JpaUserEntity entity) {
        return User.rehydrate(
                new UserId(entity.getId()),
                entity.getEmail(),
                entity.getPasswordHash(),
                UserStatus.valueOf(entity.getStatus()),
                entity.getCreatedAt()
        );
    }



}
