package com.develop_ping.union.party.exception;

import lombok.Getter;

@Getter
public class AlreadyJoinedException extends RuntimeException {
    private final String message;

    public AlreadyJoinedException(String message) {
        this.message = message;
    }
}
