package com.develop_ping.union.user.domain.service;

import com.develop_ping.union.auth.domain.OauthUserManager;
import com.develop_ping.union.auth.domain.RefreshTokenManager;
import com.develop_ping.union.auth.domain.TokenManager;
import com.develop_ping.union.auth.domain.entity.OauthUser;
import com.develop_ping.union.user.domain.UserManager;
import com.develop_ping.union.user.domain.dto.SignUpCommand;
import com.develop_ping.union.user.domain.dto.UserInfo;
import com.develop_ping.union.user.domain.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService{
    public static Duration ACCESS_TOKEN_DURATION = Duration.ofMinutes(30);
    public static Duration REFRESH_TOKEN_DURATION = Duration.ofDays(14);

    private final OauthUserManager oauthUserManager;
    private final UserManager userManager;
    private final TokenManager tokenManager;
    private final RefreshTokenManager refreshTokenManager;
    @Override
    public UserInfo signUp(SignUpCommand command) {
        // 유저 임시 토큰 검증
        log.info("임시 사용자 검색");
        OauthUser oauthUser = oauthUserManager.findByToken(command.getOauthUserToken());
        oauthUserManager.delete(oauthUser);

        // 유저 생성
        log.info("새 유저 생성");
        User user = User.of(command, oauthUser.getEmail(), oauthUser.getProfileImage());
        userManager.save(user);

        // JWT 토큰 생성
        log.info("JWT 토큰 생성");
        String accessToken = tokenManager.generateToken(user, ACCESS_TOKEN_DURATION);
        String refreshToken = tokenManager.generateToken(user, REFRESH_TOKEN_DURATION);
        refreshTokenManager.saveRefreshToken(user.getId(), refreshToken);

        //dto 변환
        return UserInfo.of(user, accessToken, refreshToken);
    }
}
