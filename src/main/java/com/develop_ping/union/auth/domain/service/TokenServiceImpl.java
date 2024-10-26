package com.develop_ping.union.auth.domain.service;

import com.develop_ping.union.auth.domain.OauthUserManager;
import com.develop_ping.union.auth.domain.RefreshTokenManager;
import com.develop_ping.union.auth.domain.TokenManager;
import com.develop_ping.union.auth.domain.entity.OauthUser;
import com.develop_ping.union.auth.exception.InvalidTokenException;
import com.develop_ping.union.user.domain.entity.User;
import com.develop_ping.union.user.domain.UserManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
@Service
public class TokenServiceImpl implements TokenService{

    private final TokenManager tokenManager;
    private final RefreshTokenManager refreshTokenManager;
    private final UserManager userManager;
    private final OauthUserManager oauthUserManager;

    @Override
    public String createNewAccessToken(String refreshToken, String token) {
        log.info("새 액세스 토큰 발급 요청");

        tokenManager.validToken(refreshToken);
        User user = userManager.findByToken(token);
        Long userId =  refreshTokenManager.findByRefreshToken(refreshToken).getUserId();

        if (!Objects.equals(user.getId(), userId)) {
            throw new InvalidTokenException();
        }

        log.info("새 액세스 토큰 발급 완료: {}", userId);
        return tokenManager.generateToken(user, Duration.ofHours(2));
    }

    @Override
    public String findPhoto(String oauthUserToken) {
        log.info("oauth에서 받아온 기본 이미지 반환");
        OauthUser oauthUser =  oauthUserManager.findByToken(oauthUserToken);
        return oauthUser.getProfileImage();
    }
}
