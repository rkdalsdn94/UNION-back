package com.develop_ping.union.user.exception;

import lombok.Getter;

@Getter
public class UserNotFoundException extends RuntimeException {
    private final String user;

    public UserNotFoundException(String user) {
        this.user = user;
    }
}
