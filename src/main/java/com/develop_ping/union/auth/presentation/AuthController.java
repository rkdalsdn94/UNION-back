package com.develop_ping.union.auth.presentation;

import com.develop_ping.union.auth.domain.service.TokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@Slf4j
public class AuthController {

    private final TokenService tokenService;

    @PostMapping("/user/token")
    public ResponseEntity<Void> createNewAccessToken(@RequestHeader("Refresh-Token") String refreshToken) {
        log.info("Received request to create new access token");

        String newAccessToken = tokenService.createNewAccessToken(refreshToken);

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
}
