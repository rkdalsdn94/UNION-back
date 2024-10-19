package com.develop_ping.union.auth.application;

public interface TokenService {
    String createNewAccessToken(String refreshToken);
}
