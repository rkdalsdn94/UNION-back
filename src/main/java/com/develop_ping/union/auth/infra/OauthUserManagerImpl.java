package com.develop_ping.union.auth.infra;

import com.develop_ping.union.auth.domain.entity.OauthUser;
import com.develop_ping.union.auth.domain.OauthUserManager;
import com.develop_ping.union.auth.exception.OauthNotPreparedException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Slf4j
public class OauthUserManagerImpl implements OauthUserManager {
    private final OauthUserRepository oauthUserRepository;

    @Override
    @Transactional(readOnly = true)
    public OauthUser findByToken(String token) {
        log.info("토큰으로 OAuth 유저를 조회합니다. 토큰: {}", token);
        return oauthUserRepository.findByToken(token).orElseThrow(OauthNotPreparedException::new);
    }

    @Override
    @Transactional
    public OauthUser save(OauthUser oauthUser) {
        log.info("OAuth 임시 유저를 저장합니다. 이메일: {}", oauthUser.getEmail());
        return oauthUserRepository.save(oauthUser);
    }

    @Override
    @Transactional(readOnly = true)
    public OauthUser findByEmail(String email) {
        log.info("이메일로 OAuth 임시 유저를 조회합니다. 이메일: {}", email);
        return oauthUserRepository.findByEmail(email).orElseThrow(OauthNotPreparedException::new);
    }

    @Override
    public void delete(OauthUser oauthUser) {
        log.info("임시 유저 인증 완료. 임시 테이블에서 해당 유저 데이터를 삭제합니다. 이메일: {}", oauthUser.getEmail());
        oauthUserRepository.delete(oauthUser);
    }

    @Override
    public boolean existsByEmail(String email) {
        log.info("이메일로 임시 유저의 존재 여부를 확인합니다. 이메일: {}", email);
        return oauthUserRepository.existsByEmail(email);
    }
}
