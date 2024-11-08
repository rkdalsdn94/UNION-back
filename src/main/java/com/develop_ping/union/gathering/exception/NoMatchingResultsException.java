package com.develop_ping.union.gathering.exception;

import lombok.Getter;

@Getter
public class NoMatchingResultsException extends RuntimeException {
    private final String message;

    public NoMatchingResultsException(String message) {
        this.message = message;
    }
}
