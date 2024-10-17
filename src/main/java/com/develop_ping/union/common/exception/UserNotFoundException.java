package com.develop_ping.union.common.exception;

import lombok.Getter;

import java.security.Principal;

@Getter
public class UserNotFoundException extends RuntimeException {
    private final Object userId;

    public UserNotFoundException(Object userId) {
        this.userId = userId;
    }
}
