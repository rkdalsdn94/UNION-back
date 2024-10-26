package com.develop_ping.union.auth.domain;

import com.develop_ping.union.user.domain.entity.User;

public interface OAuthUnlinkManager {
    /**
     * 주어진 유저의 OAuth 연결을 해제합니다.
     *
     * @param user 연결 해제를 요청한 사용자
     */
    void unlinkUser(User user);
}

