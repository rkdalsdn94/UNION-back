package com.develop_ping.union.party.exception;

import lombok.Getter;

@Getter
public class ParticipationNotFoundException extends RuntimeException {
    private final String message;

    public ParticipationNotFoundException(String message) {
        this.message = message;
    }
}
