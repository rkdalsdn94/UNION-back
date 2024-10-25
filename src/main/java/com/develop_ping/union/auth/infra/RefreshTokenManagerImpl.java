package com.develop_ping.union.auth.infra;

import com.develop_ping.union.auth.domain.entity.RefreshToken;
import com.develop_ping.union.auth.domain.RefreshTokenManager;
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
    public void deleteByUserId(Long userId) {
        log.info("리프레시 토큰 삭제 시도");

        // 토큰을 조회하고 바로 삭제
        RefreshToken token = refreshTokenRepository.findByUserId(userId)
                .orElseThrow(() -> new UserNotFoundException(userId.toString()));
        refreshTokenRepository.delete(token);

        log.info("리프레시 토큰 삭제 완료: {}", userId);
    }

    @Override
    @Transactional(readOnly = true)
    public RefreshToken findByRefreshToken(String refreshToken) {
        log.info("리프레시 토큰 검색 시도: {}", refreshToken);
        return refreshTokenRepository.findByRefreshToken(refreshToken)
                .orElseThrow(InvalidTokenException::new);
    }

    @Override
    @Transactional
    public void saveRefreshToken(Long userId, String refreshToken) {
        log.info("리프레시 토큰 생성 시도: {}", userId);
        RefreshToken tokenEntity = refreshTokenRepository.findById(userId)
                .orElseGet(() -> {
                    log.info("기존 토큰이 없어 신규 생성: {}", userId);
                    return new RefreshToken(userId, refreshToken);
                });
        log.info("생성 완료: {}", userId);
        tokenEntity.update(refreshToken);
        refreshTokenRepository.save(tokenEntity);
        log.info("업데이트 완료: {}", userId);
    }
}
