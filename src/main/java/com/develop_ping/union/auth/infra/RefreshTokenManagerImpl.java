package com.develop_ping.union.auth.infra;

import com.develop_ping.union.auth.domain.entity.RefreshToken;
import com.develop_ping.union.auth.domain.RefreshTokenManager;
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
    public void deleteByToken(String refreshToken) {
        log.info("Attempting to delete refresh token: {}", refreshToken);

        // 토큰을 조회하고 바로 삭제
        RefreshToken token = refreshTokenRepository.findByRefreshToken(refreshToken)
                .orElseThrow(InvalidTokenException::new);
        refreshTokenRepository.delete(token);

        log.info("Refresh token deleted successfully: {}", refreshToken);
    }

    @Override
    @Transactional
    public RefreshToken findByRefreshToken(String refreshToken) {
        log.info("Searching for refresh token: {}", refreshToken);
        return refreshTokenRepository.findByRefreshToken(refreshToken)
                .orElseThrow(InvalidTokenException::new);
    }

    @Override
    @Transactional
    public void saveRefreshToken(Long userId, String refreshToken) {
        log.info("Saving refresh token for user ID: {}", userId);
        RefreshToken tokenEntity = refreshTokenRepository.findById(userId)
                .orElseGet(() -> {
                    log.info("No existing refresh token found for user ID: {}, creating a new one", userId);
                    return new RefreshToken(userId, refreshToken);
                });
        log.info("Refresh token found or created for user ID: {}", userId);
        tokenEntity.update(refreshToken);
        refreshTokenRepository.save(tokenEntity);
        log.info("Refresh token updated successfully for user ID: {}", userId);
    }
}
