package com.develop_ping.union.auth.domain.service;

import com.develop_ping.union.auth.domain.dto.OAuth2ACommand;
import com.develop_ping.union.auth.domain.OauthUserManager;
import com.develop_ping.union.auth.domain.entity.OauthUser;
import com.develop_ping.union.auth.exception.OauthNotPreparedException;
import com.develop_ping.union.user.domain.UserManager;
import com.develop_ping.union.user.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {
    private final OauthUserManager oauthUserManager;
    private final UserManager userManager;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        String accessToken = userRequest.getAccessToken().getTokenValue();
        log.info("oauth 액세스 토큰 : {}", accessToken);

        String provider = userRequest.getClientRegistration().getRegistrationId();
        String userNameAttributeName = userRequest.getClientRegistration()
                .getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();

        log.info("OAuth 로그인 요청 받음. 서비스 제공자 : {}", provider);

        OAuth2ACommand oauth2ACommand = OAuth2ACommand.of(provider, userNameAttributeName, oAuth2User.getAttributes());

        String email = oauth2ACommand.getEmail();
        String picture = oauth2ACommand.getPicture();
        log.info("OAuth 사용자 이메일 : {}", email);
        log.info("OAuth 사용자 사진 : {}", picture);

        if(!userManager.existsByEmail(email) && !oauthUserManager.existsByEmail(email)) {
            OauthUser oauthUser = OauthUser.builder()
                    .token(UUID.randomUUID().toString())
                    .email(email)
                    .profileImage(picture)
                    .provider(provider)
                    .oauthAccessToken(accessToken)
                    .build();
            oauthUserManager.save(oauthUser);
        }

        return new DefaultOAuth2User(
                oAuth2User.getAuthorities(),
                oauth2ACommand.convertToMap(),
                "email"
        );
    }
}

