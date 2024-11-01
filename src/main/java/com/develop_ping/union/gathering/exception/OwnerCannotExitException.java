package com.develop_ping.union.gathering.exception;

import lombok.Getter;

@Getter
public class OwnerCannotExitException extends RuntimeException {
    private final String message;

    public OwnerCannotExitException(String message) {
        this.message = message;
    }
}
