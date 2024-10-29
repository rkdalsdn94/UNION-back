package com.develop_ping.union.auth.domain;


import com.develop_ping.union.auth.domain.entity.RefreshToken;
import com.develop_ping.union.user.domain.entity.User;

public interface RefreshTokenManager {

    // 리프레시 토큰 삭제
    void deleteByUserId(User user);

    // 리프레시 토큰 검색
    RefreshToken findByRefreshToken(String refreshToken);

    // 리프레시 토큰 저장
    void saveRefreshToken(User user, String refreshToken);

}
