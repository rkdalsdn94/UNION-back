package com.develop_ping.union.auth.domain;

import com.develop_ping.union.auth.infra.OauthUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

public interface OauthUserManager {
    OauthUser findByToken(String token);
    void save(String email, String photoUrl);

    OauthUser findByEmail(String email);
}
