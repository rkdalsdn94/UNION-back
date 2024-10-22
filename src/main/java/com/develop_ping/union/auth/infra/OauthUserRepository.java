package com.develop_ping.union.auth.infra;

import com.develop_ping.union.auth.domain.OauthUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OauthUserRepository extends JpaRepository<OauthUser, Long> {
    Optional<OauthUser> findByToken(String token);
}
