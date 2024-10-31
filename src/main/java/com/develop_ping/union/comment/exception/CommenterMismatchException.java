package com.develop_ping.union.comment.exception;

import lombok.Getter;

@Getter
public class CommenterMismatchException extends RuntimeException {
    private final String nickname;

    public CommenterMismatchException(String nickname) {
        this.nickname = nickname;
    }
}
