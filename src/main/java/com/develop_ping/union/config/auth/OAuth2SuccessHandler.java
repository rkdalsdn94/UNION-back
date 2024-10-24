package com.develop_ping.union.config.auth;

import com.develop_ping.union.auth.domain.entity.OauthUser;
import com.develop_ping.union.auth.domain.OauthUserManager;
import com.develop_ping.union.auth.domain.RefreshTokenManager;
import com.develop_ping.union.auth.domain.TokenManager;
import com.develop_ping.union.user.domain.entity.User;
import com.develop_ping.union.user.domain.UserManager;
import com.develop_ping.union.user.exception.UserNotFoundException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.time.Duration;

@Slf4j
@RequiredArgsConstructor
@Component
public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    public static String REDIRECT_URL_USER_NOT_FOUND = "https://union-frontend-rust.vercel.app/LoginCallback";
    public static String REDIRECT_URL_USER_EXIST = "https://union-frontend-rust.vercel.app/Home";
    public static Duration ACCESS_TOKEN_DURATION = Duration.ofMinutes(30);
    public static Duration REFRESH_TOKEN_DURATION = Duration.ofDays(14);

    private final UserManager userManager;
    private final OauthUserManager oauthUserManager;
    private final TokenManager tokenManager;
    private final RefreshTokenManager refreshTokenManager;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        String email = (String) oAuth2User.getAttributes().get("email");

        try {
            User user = userManager.findByEmail(email);

            // 토큰 생성 및 등록
            String accessToken = tokenManager.generateToken(user, ACCESS_TOKEN_DURATION);
            String refreshToken = tokenManager.generateToken(user, REFRESH_TOKEN_DURATION);
            refreshTokenManager.saveRefreshToken(user.getId(), refreshToken);

            //리다이렉트 URL 생성, 차후에 쿠키 방식으로 리펙토링
            String redirectUrl = ServletUriComponentsBuilder
                    .fromUriString(REDIRECT_URL_USER_EXIST)
                    .queryParam("accessToken", accessToken)
                    .queryParam("refreshToken", refreshToken)
                    .build()
                    .toUriString();

            getRedirectStrategy()
                    .sendRedirect(request, response, redirectUrl);

        } catch (UserNotFoundException e) {
            // 유저가 없을 경우 임시 테이블의 유저 토큰과 함께 리다이렉트
            OauthUser oauthUser = oauthUserManager.findByEmail(email);
            getRedirectStrategy().sendRedirect(request, response, REDIRECT_URL_USER_NOT_FOUND + "?oauthUserToken=" + oauthUser.getToken());
        }

    }
}
