package com.develop_ping.union.common.exception;

import lombok.Getter;

@Getter
public class GatheringNotFoundException extends RuntimeException {
    private final Long gatheringId;

    public GatheringNotFoundException(Long gatheringId) {
        this.gatheringId = gatheringId;
    }
}
