package com.develop_ping.union.auth.application;

import com.develop_ping.union.auth.application.dto.OAuth2ACommand;
import com.develop_ping.union.auth.domain.OauthUserManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {
    public static String BASE_PHOTO_URL = "https://union-image-bucket.s3.ap-northeast-2.amazonaws.com/userToken/44009c26-13fc-4912-a3db-7cac644e39c5.png";
    private final OauthUserManager oauthUserManager;
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        // 1. 기본 제공되는 사용자 정보 가져오기
        OAuth2User oAuth2User = super.loadUser(userRequest);

        // 2. OAuth2 제공자 정보 가져오기 (Google, Kakao, Naver 등)
        String provider = userRequest.getClientRegistration().getRegistrationId();
        String userNameAttributeName = userRequest.getClientRegistration()
                .getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();

        // 3. 제공자에 따라 사용자 정보 변환하기 (Google, Kakao, Naver)
        OAuth2ACommand oauth2ACommand = OAuth2ACommand.of(provider, userNameAttributeName, oAuth2User.getAttributes());

        // 4. 이메일과 프로필 이미지 URL 가져오기
        String email = oauth2ACommand.getEmail();
        String picture = oauth2ACommand.getPicture();

        // 5. 이미지가 없으면 기본 이미지로 설정
        if (picture.isEmpty()) picture = BASE_PHOTO_URL;


        // 6. OauthUserManager를 통해 사용자 저장
        oauthUserManager.save(email, picture);

        return oAuth2User;
    }
}

