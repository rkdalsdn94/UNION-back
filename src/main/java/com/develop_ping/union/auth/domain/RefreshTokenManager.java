package com.develop_ping.union.auth.domain;


public interface RefreshTokenManager {

    // 리프레시 토큰 삭제
    void deleteByToken(String refreshToken);

    // 리프레시 토큰 검색
    RefreshToken findByRefreshToken(String refreshToken);

    // 리프레시 토큰 저장
    void saveRefreshToken(Long userId, String refreshToken);
}
