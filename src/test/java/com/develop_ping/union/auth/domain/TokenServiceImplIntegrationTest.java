package com.develop_ping.union.auth.domain;

import com.develop_ping.union.IntegrationTestSupport;
import com.develop_ping.union.auth.domain.entity.OauthUser;
import com.develop_ping.union.auth.domain.service.TokenServiceImpl;
import com.develop_ping.union.auth.exception.InvalidTokenException;
import com.develop_ping.union.auth.exception.OauthNotPreparedException;
import com.develop_ping.union.user.domain.entity.User;
import com.develop_ping.union.user.domain.UserManager;
import com.develop_ping.union.user.exception.UserNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
@DisplayName("TokenService 통합 테스트")
class TokenServiceImplIntegrationTest {

    @Autowired
    private TokenServiceImpl tokenService;

    @Autowired
    private RefreshTokenManager refreshTokenManager;

    @Autowired
    private UserManager userManager;

    @Autowired
    private OauthUserManager oauthUserManager;

    @MockBean
    private TokenManager tokenManager;

    @BeforeEach
    @Transactional
    void setup() {
        // 테스트용 User 데이터 저장
        User user = User.builder()
                .email("test@example.com")
                .token(UUID.randomUUID().toString())
                .nickname("testUser")
                .description("test description")
                .profileImage("https://example.com/profile.jpg")
                .univName("Test University")
                .build();
        userManager.save(user);

        // 테스트용 RefreshToken 데이터 저장
        refreshTokenManager.saveRefreshToken(user.getId(), "sample-refresh-token");

        OauthUser oauthUser = OauthUser.builder()
                .token("sample-oauth-token")
                .email("oauth@example.com")
                .profileImage("https://example.com/profile.jpg")
                .build();
        oauthUserManager.save(oauthUser);

        // Mock 설정
        when(tokenManager.generateToken(any(User.class), any(Duration.class)))
                .thenReturn("new-access-token"); // 액세스 토큰 반환 설정
    }

    @DisplayName("리프레시 토큰으로 새 액세스 토큰을 발급한다.")
    @Test
    @Transactional
    void createNewAccessToken_ShouldGenerateNewAccessToken() {
        // Given
        String refreshToken = "sample-refresh-token";

        // When
        String result = tokenService.createNewAccessToken(refreshToken);

        // Then
        assertThat(result).isNotNull();
        assertThat(result).isNotEmpty();
    }

    @DisplayName("유효하지 않은 리프레시 토큰으로 새 액세스 토큰을 발급하려고 하면 예외가 발생한다.")
    @Test
    @Transactional
    void createNewAccessToken_InvalidToken_ShouldThrowException() {
        // Given
        String invalidRefreshToken = "invalid-refresh-token";

        // When & Then
        assertThatThrownBy(() -> tokenService.createNewAccessToken(invalidRefreshToken))
                .isInstanceOf(InvalidTokenException.class);
    }

    @DisplayName("존재하지 않는 유저 ID로 새 액세스 토큰을 발급하려고 하면 예외가 발생한다.")
    @Test
    @Transactional
    void createNewAccessToken_UserNotFound_ShouldThrowException() {
        // Given
        String refreshToken = "sample-refresh-token";

        // 삭제된 사용자 시나리오를 위해 유저 삭제
        User user = userManager.findByEmail("test@example.com");
        userManager.delete(user);

        // When & Then
        assertThatThrownBy(() -> tokenService.createNewAccessToken(refreshToken))
                .isInstanceOf(UserNotFoundException.class);
    }

    @DisplayName("OAuth 사용자 기본 이미지를 반환한다.")
    @Test
    @Transactional
    void findPhoto_ShouldReturnProfileImage() {
        // Given
        String oauthUserToken = "sample-oauth-token";

        // When
        String result = tokenService.findPhoto(oauthUserToken);

        // Then
        assertThat(result).isEqualTo("https://example.com/profile.jpg");
    }

    @DisplayName("존재하지 않는 OAuth 사용자 토큰으로 기본 이미지를 요청하면 예외가 발생한다.")
    @Test
    @Transactional
    void findPhoto_OauthUserNotPrepared_ShouldThrowException() {
        // Given
        String invalidOauthUserToken = "invalid-oauth-token";

        // When & Then
        assertThatThrownBy(() -> tokenService.findPhoto(invalidOauthUserToken))
                .isInstanceOf(OauthNotPreparedException.class);
    }
}
