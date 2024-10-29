package com.develop_ping.union.auth.infra;

import com.develop_ping.union.auth.domain.entity.RefreshToken;
import com.develop_ping.union.user.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByRefreshToken(String refreshToken);
    Optional<RefreshToken> findByUser(User user);
}
