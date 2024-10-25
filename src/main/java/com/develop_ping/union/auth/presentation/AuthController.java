package com.develop_ping.union.auth.presentation;

import com.develop_ping.union.auth.domain.TokenManager;
import com.develop_ping.union.auth.domain.service.TokenService;
import com.develop_ping.union.user.domain.UserManager;
import com.develop_ping.union.user.domain.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;

@RequiredArgsConstructor
@RestController
@Slf4j
public class AuthController {

    private final TokenService tokenService;
    private final TokenManager tokenManager; // 테스트용
    private final UserManager userManager; // 테스트용

    @PostMapping("/user/token")
    public ResponseEntity<Void> createNewAccessToken(@RequestHeader("Refresh-Token") String refreshToken,
                                                     @RequestParam String token) {
        log.info("새 토큰 발급 요청 받음");

        String newAccessToken = tokenService.createNewAccessToken(refreshToken, token);

        // 새로운 액세스 토큰을 헤더에 추가
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.AUTHORIZATION, "Bearer " + newAccessToken);

        // 생성 성공 201 반환
        return ResponseEntity.status(HttpStatus.CREATED)
                .headers(headers)
                .build();
    }

    @GetMapping("/oauth/photo")
    public ResponseEntity<String> findDefaultPhoto (@RequestParam String oauthUserToken) {
        return ResponseEntity.ok(tokenService.findPhoto(oauthUserToken));
    }

    // 테스트용 액세스 토큰
    @GetMapping("/test/token/{userId}")
    private ResponseEntity<Void> testToken (@PathVariable Long userId) {
        User user = userManager.findById(userId);
        String accessToken = tokenManager.generateToken(user, Duration.ofDays(30));

        // 새로운 액세스 토큰을 헤더에 추가
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken);

        // 생성 성공 201 반환
        return ResponseEntity.status(HttpStatus.CREATED)
                .headers(headers)
                .build();
    }

}
