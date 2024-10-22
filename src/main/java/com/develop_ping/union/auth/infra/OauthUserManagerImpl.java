package com.develop_ping.union.auth.infra;

import com.develop_ping.union.auth.domain.OauthUser;
import com.develop_ping.union.auth.domain.OauthUserManager;
import com.develop_ping.union.auth.exception.OauthNotPreparedException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class OauthUserManagerImpl implements OauthUserManager {
    private final OauthUserRepository oauthUserRepository;

    @Override
    public OauthUser findByToken(String token) {
        return oauthUserRepository.findByToken(token).orElseThrow(OauthNotPreparedException::new);
    }

    @Override
    public String save(String email, String photoUrl) {
        OauthUser user = OauthUser.builder()
                .email(email)
                .profileImage(photoUrl)
                .token(UUID.randomUUID().toString())
                .build();

        oauthUserRepository.save(user);
        return user.getToken();
    }
}
