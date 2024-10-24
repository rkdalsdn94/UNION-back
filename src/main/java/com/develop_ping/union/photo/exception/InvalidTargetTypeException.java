package com.develop_ping.union.photo.exception;

import lombok.Getter;

@Getter
public class InvalidTargetTypeException extends RuntimeException {
    private final String targetType;

    public InvalidTargetTypeException(String targetType) {
        this.targetType = targetType;
    }
}
