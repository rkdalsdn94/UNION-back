package com.develop_ping.union.auth.domain.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Slf4j
@Service
public class OAuthUnlinkService {
    private final RestTemplate restTemplate = new RestTemplate();

    // 카카오, 네이버, 구글 각각의 클라이언트 ID와 해제 URL 설정
    private static final String KAKAO_UNLINK_URL = "https://kapi.kakao.com/v1/user/unlink";
    private static final String NAVER_UNLINK_URL = "https://nid.naver.com/oauth2.0/token";
    private static final String GOOGLE_UNLINK_URL = "https://accounts.google.com/o/oauth2/revoke";

    public void unlinkUser(@AuthenticationPrincipal OAuth2User oAuth2User, String provider) {
        String accessToken = (String) oAuth2User.getAttribute("accessToken"); // 액세스 토큰 가져오기
        log.info("회원 탈퇴 요청 - 제공자: {}, 액세스 토큰: {}", provider, accessToken);

        switch (provider) {
            case "kakao":
                unlinkKakaoUser(accessToken);
                break;
            case "naver":
                unlinkNaverUser(accessToken);
                break;
            case "google":
                unlinkGoogleUser(accessToken);
                break;
            default:
                throw new IllegalArgumentException("지원하지 않는 제공자입니다.");
        }
    }

    private void unlinkKakaoUser(String accessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken); // Authorization: Bearer {accessToken}

        RequestEntity<Void> request = new RequestEntity<>(headers, HttpMethod.POST, URI.create(KAKAO_UNLINK_URL));
        ResponseEntity<String> response = restTemplate.exchange(request, String.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            log.info("카카오 사용자 연결 해제 완료");
        } else {
            log.warn("카카오 사용자 연결 해제 실패: {}", response.getBody());
        }
    }

    private void unlinkNaverUser(String accessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken); // Authorization: Bearer {accessToken}

        // 네이버의 경우 액세스 토큰 해제 요청에 필요하다면 추가 정보 첨부 가능
        RequestEntity<Void> request = new RequestEntity<>(headers, HttpMethod.GET,
                URI.create(NAVER_UNLINK_URL + "?grant_type=delete&access_token=" + accessToken));
        ResponseEntity<String> response = restTemplate.exchange(request, String.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            log.info("네이버 사용자 연결 해제 완료");
        } else {
            log.warn("네이버 사용자 연결 해제 실패: {}", response.getBody());
        }
    }

    private void unlinkGoogleUser(String accessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken); // Authorization: Bearer {accessToken}

        RequestEntity<Void> request = new RequestEntity<>(headers, HttpMethod.GET,
                URI.create(GOOGLE_UNLINK_URL + "?token=" + accessToken));
        ResponseEntity<String> response = restTemplate.exchange(request, String.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            log.info("구글 사용자 연결 해제 완료");
        } else {
            log.warn("구글 사용자 연결 해제 실패: {}", response.getBody());
        }
    }
}

