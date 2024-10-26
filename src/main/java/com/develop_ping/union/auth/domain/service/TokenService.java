package com.develop_ping.union.auth.domain.service;

public interface TokenService {
    String createNewAccessToken(String refreshToken, String oauthUserToken);

    String findPhoto(String oauthUserToken);
}
