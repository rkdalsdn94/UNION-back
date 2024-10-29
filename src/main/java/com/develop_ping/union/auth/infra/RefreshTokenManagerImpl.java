package com.develop_ping.union.auth.infra;

import com.develop_ping.union.auth.domain.entity.RefreshToken;
import com.develop_ping.union.auth.domain.RefreshTokenManager;
import com.develop_ping.union.user.domain.entity.User;
import com.develop_ping.union.user.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import com.develop_ping.union.auth.exception.InvalidTokenException;

@Slf4j
@Component
@RequiredArgsConstructor
public class RefreshTokenManagerImpl implements RefreshTokenManager {

    private final RefreshTokenRepository refreshTokenRepository;

    @Override
    @Transactional
    public void deleteByUserId(User user) {
        log.info("리프레시 토큰 삭제 시도. 사용자 nickname: {}", user.getNickname());

        // 토큰을 조회하고 바로 삭제
        RefreshToken token = refreshTokenRepository.findByUser(user)
                .orElseThrow(() -> new UserNotFoundException(user.getNickname()));
        refreshTokenRepository.delete(token);

        log.info("리프레시 토큰 삭제 완료. 사용자 nickname: {}", user.getNickname());
    }

    @Override
    @Transactional(readOnly = true)
    public RefreshToken findByRefreshToken(String refreshToken) {
        log.info("리프레시 토큰 검색 시도. 토큰: {}", refreshToken);
        return refreshTokenRepository.findByRefreshToken(refreshToken)
                .orElseThrow(InvalidTokenException::new);
    }

    @Override
    @Transactional
    public void saveRefreshToken(User user, String refreshToken) {
        log.info("리프레시 토큰 생성 또는 업데이트 시도. 사용자 nickname: {}", user.getNickname());
        RefreshToken tokenEntity = refreshTokenRepository.findByUser(user)
                .orElseGet(() -> {
                    log.info("기존 리프레시 토큰이 없어 신규 생성. 사용자 nickname: {}", user.getNickname());
                    return new RefreshToken(user, refreshToken);
                });
        tokenEntity.update(refreshToken);
        refreshTokenRepository.save(tokenEntity);
        log.info("리프레시 토큰 저장 완료. 사용자 nickname: {}", user.getNickname());
    }
}
