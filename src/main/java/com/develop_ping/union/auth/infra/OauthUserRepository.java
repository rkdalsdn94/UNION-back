package com.develop_ping.union.auth.infra;

import com.develop_ping.union.auth.domain.entity.OauthUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OauthUserRepository extends JpaRepository<OauthUser, Long> {
    Optional<OauthUser> findByToken(String token);

    Optional<OauthUser> findByEmail(String email);

    boolean existsByEmail(String email);
}

