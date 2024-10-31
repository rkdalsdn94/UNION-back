package com.develop_ping.union.gathering.exception;

import lombok.Getter;

@Getter
public class GatheringPermissionDeniedException extends RuntimeException {

    private final Long ownerId;
    private final Long userId;

    public GatheringPermissionDeniedException(Long ownerId, Long userId) {
        this.ownerId = ownerId;
        this.userId = userId;
    }
}
