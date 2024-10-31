package com.develop_ping.union.gathering.exception;

import lombok.Getter;

@Getter
public class ParticipantLimitExceededException extends RuntimeException {
    private final String message;

    public ParticipantLimitExceededException(String message) {
        this.message = message;
    }
}
