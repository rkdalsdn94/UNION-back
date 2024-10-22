package com.develop_ping.union.auth.domain;

import com.develop_ping.union.auth.infra.OauthUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

public interface OauthUserManager {
    OauthUser findByToken(String token);
    String save(String email, String photoUrl);
}
