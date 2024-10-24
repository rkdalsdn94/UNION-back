package com.develop_ping.union.auth.domain.service;

import com.develop_ping.union.auth.domain.RefreshTokenManager;
import com.develop_ping.union.auth.domain.TokenManager;
import com.develop_ping.union.user.domain.entity.User;
import com.develop_ping.union.user.domain.UserManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Slf4j
@RequiredArgsConstructor
@Service
public class TokenServiceImpl implements TokenService{

    private final TokenManager tokenManager;
    private final RefreshTokenManager refreshTokenManager;
    private final UserManager userManager;

    @Override
    public String createNewAccessToken(String refreshToken) {
        log.info("Received request to create a new Access Token");

        tokenManager.validToken(refreshToken);
        Long userId =  refreshTokenManager.findByRefreshToken(refreshToken).getUserId();
        User user = userManager.findById(userId);

        log.info("Creating new Access Token for user ID: {}", userId);
        return tokenManager.generateToken(user, Duration.ofHours(2));
    }
}
