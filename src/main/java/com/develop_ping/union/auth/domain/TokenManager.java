package com.develop_ping.union.auth.domain;

import com.develop_ping.union.user.domain.entity.User;
import org.springframework.security.core.Authentication;

import java.time.Duration;

public interface TokenManager {

    // JWT 토큰 생성 메서드
    String generateToken(User user, Duration expiredAt);

    // JWT 토큰 유효성 검증 메서드
    boolean validToken(String token);

    // JWT 토큰에서 인증 정보를 가져오는 메서드
    Authentication getAuthentication(String token);
}
