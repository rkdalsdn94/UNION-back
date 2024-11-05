package com.develop_ping.union.gathering.exception;

import lombok.Getter;

@Getter
public class RecruitmentAlreadyCompletedException extends RuntimeException {
    private final String message;

    public RecruitmentAlreadyCompletedException(String message) {
        this.message = message;
    }
}
