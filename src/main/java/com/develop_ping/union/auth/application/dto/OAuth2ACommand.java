package com.develop_ping.union.auth.application.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;

@ToString
@Builder(access = AccessLevel.PRIVATE)
@Getter
public class OAuth2ACommand {
    private Map<String, Object> attributes;
    private String attributeKey;
    private String email;
    private String picture;

    public static OAuth2ACommand of(String provider, String attributeKey,
                                    Map<String, Object> attributes) {
        switch (provider) {
            case "google":
                return ofGoogle(attributeKey, attributes);
            case "kakao":
                return ofKakao("email", attributes);
            case "naver":
                return ofNaver("id", attributes);
            default:
                throw new RuntimeException();
        }
    }

    private static OAuth2ACommand ofGoogle(String attributeKey,
                                           Map<String, Object> attributes) {
        return OAuth2ACommand.builder()
                .email((String) attributes.get("email"))
                .picture((String)attributes.get("picture"))
                .attributes(attributes)
                .attributeKey(attributeKey)
                .build();
    }

    private static OAuth2ACommand ofKakao(String attributeKey,
                                          Map<String, Object> attributes) {
        Map<String, Object> kakaoAccount = (Map<String, Object>) attributes.get("kakao_account");
        Map<String, Object> kakaoProfile = (Map<String, Object>) kakaoAccount.get("profile");

        return OAuth2ACommand.builder()
                .email((String) kakaoAccount.get("email"))
                .picture((String)kakaoProfile.get("profile_image_url"))
                .attributes(kakaoAccount)
                .attributeKey(attributeKey)
                .build();
    }

    private static OAuth2ACommand ofNaver(String attributeKey,
                                          Map<String, Object> attributes) {
        Map<String, Object> response = (Map<String, Object>) attributes.get("response");

        return OAuth2ACommand.builder()
                .email((String) response.get("email"))
                .picture((String) response.get("profile_image"))
                .attributes(response)
                .attributeKey(attributeKey)
                .build();
    }

    public Map<String, Object> convertToMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("id", attributeKey);
        map.put("key", attributeKey);
        map.put("email", email);
        map.put("picture", picture);

        return map;
    }
}
