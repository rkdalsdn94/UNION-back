package com.develop_ping.union.auth.presentation;

import com.develop_ping.union.auth.domain.TokenManager;
import com.develop_ping.union.auth.infra.OAuthUnlinkManagerImpl;
import com.develop_ping.union.auth.domain.service.TokenService;
import com.develop_ping.union.user.domain.UserManager;
import com.develop_ping.union.user.domain.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;

@RequiredArgsConstructor
@RestController
@Slf4j
public class AuthController {

    private final TokenService tokenService;
//    private final TokenManager tokenManager; // 테스트용
//    private final UserManager userManager; // 테스트용

    @PostMapping("/user/token")
    public ResponseEntity<Void> createNewAccessToken(@RequestHeader("Refresh-Token") String refreshToken,
                                                     @RequestParam String token) {
        log.info("새로운 액세스 토큰 발급 요청을 받았습니다.");

        String newAccessToken = tokenService.createNewAccessToken(refreshToken, token);

        // 새로운 액세스 토큰을 헤더에 추가
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.AUTHORIZATION, "Bearer " + newAccessToken);

        log.info("새로운 액세스 토큰이 발급되었습니다. 토큰: {}", newAccessToken);

        // 생성 성공 201 반환
        return ResponseEntity.status(HttpStatus.CREATED)
                .headers(headers)
                .build();
    }

    @GetMapping("/oauth/photo")
    public ResponseEntity<String> findDefaultPhoto(@RequestParam String oauthUserToken) {
        log.info("OAuth 사용자 기본 프로필 사진 요청: {}", oauthUserToken);
        String photoUrl = tokenService.findPhoto(oauthUserToken);
        log.info("프로필 사진 URL 반환 완료: {}", photoUrl);
        return ResponseEntity.ok(photoUrl);
    }

//    // 테스트용 액세스 토큰
//    @GetMapping("/test/token/{userId}")
//    public ResponseEntity<Void> testToken (@PathVariable Long userId) {
//        User user = userManager.findById(userId);
//        String accessToken = tokenManager.generateToken(user, Duration.ofDays(30));
//
//        // 새로운 액세스 토큰을 헤더에 추가
//        HttpHeaders headers = new HttpHeaders();
//        headers.set(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken);
//
//        // 생성 성공 201 반환
//        return ResponseEntity.status(HttpStatus.CREATED)
//                .headers(headers)
//                .build();
//    }
}
