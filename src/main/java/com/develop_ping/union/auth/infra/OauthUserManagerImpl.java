package com.develop_ping.union.auth.infra;

import com.develop_ping.union.auth.domain.entity.OauthUser;
import com.develop_ping.union.auth.domain.OauthUserManager;
import com.develop_ping.union.auth.exception.OauthNotPreparedException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class OauthUserManagerImpl implements OauthUserManager {
    private final OauthUserRepository oauthUserRepository;

    @Override
    @Transactional(readOnly = true)
    public OauthUser findByToken(String token) {
        return oauthUserRepository.findByToken(token).orElseThrow(OauthNotPreparedException::new);
    }

    @Override
    @Transactional
    public void save(String email, String photoUrl) {
        OauthUser user = oauthUserRepository.findByEmail(email)
                .orElseGet(() -> {
                    // 사용자가 없으면 새로 생성
                    OauthUser newUser = OauthUser.builder()
                            .email(email)
                            .profileImage(photoUrl)
                            .token(UUID.randomUUID().toString())
                            .build();

                    // 새로운 사용자 저장
                    oauthUserRepository.save(newUser);
                    log.info("임시 사용자 저장 완료. email: {}", email);
                    return newUser;
                });
    }

    @Override
    @Transactional(readOnly = true)
    public OauthUser findByEmail(String email) {
        return oauthUserRepository.findByEmail(email).orElseThrow(OauthNotPreparedException::new);
    }

    @Override
    public void delete(OauthUser oauthUser) {
        oauthUserRepository.delete(oauthUser);
        log.info("임시 사용자 인증 완료. 임시 테이블에서 데이터 삭제");
    }
}
