package com.develop_ping.union.user.exception;

import lombok.Getter;

@Getter
public class DuplicateNicknameException extends RuntimeException {
    private final String nickname;

    public DuplicateNicknameException(String nickname) {
        this.nickname = nickname;
    }
}
