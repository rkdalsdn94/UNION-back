package com.develop_ping.union.common.exception;

import lombok.Getter;

@Getter
public class DuplicateNicknameException extends RuntimeException {
    private final String nickname;

    public DuplicateNicknameException(String nickname) {
        this.nickname = nickname;
    }
}
