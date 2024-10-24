package com.develop_ping.union.auth.domain;

import com.develop_ping.union.auth.domain.entity.OauthUser;

public interface OauthUserManager {
    OauthUser findByToken(String token);
    OauthUser save(OauthUser oauthUser);

    OauthUser findByEmail(String email);

    void delete(OauthUser oauthUser);
}
