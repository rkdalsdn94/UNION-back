package com.develop_ping.union.auth.infra;

import com.develop_ping.union.auth.domain.OAuthUnlinkManager;
import com.develop_ping.union.auth.domain.OauthUserManager;
import com.develop_ping.union.auth.domain.entity.OauthUser;
import com.develop_ping.union.user.domain.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Slf4j
@Service
@RequiredArgsConstructor
public class OAuthUnlinkManagerImpl implements OAuthUnlinkManager {
    private final RestTemplate restTemplate = new RestTemplate();
    private final OauthUserManager oauthUserManager;

    @Value("${spring.security.oauth2.client.registration.naver.client-id}")
    private String naverClientId;
    @Value("${spring.security.oauth2.client.registration.naver.client-secret}")
    private String naverClientSecret;


    // 카카오, 네이버, 구글 각각의 클라이언트 ID와 해제 URL 설정
    private static final String KAKAO_UNLINK_URL = "https://kapi.kakao.com/v1/user/unlink";
    private static final String NAVER_UNLINK_URL = "https://nid.naver.com/oauth2.0/token";
    private static final String GOOGLE_UNLINK_URL = "https://accounts.google.com/o/oauth2/revoke";

    public void unlinkUser(User user) {
        OauthUser oauthUser = oauthUserManager.findByEmail(user.getEmail());
        String provider = user.getProvider();
        String accessToken = oauthUser.getOauthAccessToken();

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

        // URL에 필요한 파라미터 추가
        String url = NAVER_UNLINK_URL + "?grant_type=delete"
                + "&client_id=" + naverClientId
                + "&client_secret=" + naverClientSecret
                + "&access_token=" + accessToken;

        RequestEntity<Void> request = new RequestEntity<>(headers, HttpMethod.GET, URI.create(url));
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

