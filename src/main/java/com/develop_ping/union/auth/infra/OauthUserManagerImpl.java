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
        log.info("토큰으로 유저 찾기");
        return oauthUserRepository.findByToken(token).orElseThrow(OauthNotPreparedException::new);
    }

    @Override
    @Transactional
    public OauthUser save(OauthUser oauthUser) {
        log.info("임시 유저 저장");
        return oauthUserRepository.save(oauthUser);
    }

    @Override
    @Transactional(readOnly = true)
    public OauthUser findByEmail(String email) {
        log.info("Email로 유저 찾기");
        return oauthUserRepository.findByEmail(email).orElseThrow(OauthNotPreparedException::new);
    }

    @Override
    public void delete(OauthUser oauthUser) {
        oauthUserRepository.delete(oauthUser);
        log.info("임시 사용자 인증 완료. 임시 테이블에서 데이터 삭제");
    }
}
