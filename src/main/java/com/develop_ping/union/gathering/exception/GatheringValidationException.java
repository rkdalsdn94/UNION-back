package com.develop_ping.union.gathering.exception;

import lombok.Getter;

@Getter
public class GatheringValidationException extends RuntimeException {
    private final String message;

    public GatheringValidationException(String message) {
        this.message = message;
    }
}
