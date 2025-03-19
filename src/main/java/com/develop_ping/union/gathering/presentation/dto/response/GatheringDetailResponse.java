package com.develop_ping.union.gathering.presentation.dto.response;

import com.develop_ping.union.gathering.domain.entity.Gathering;
import lombok.Builder;
import lombok.Getter;

@Getter
public class GatheringDetailResponse {

    private final Gathering gathering;
    private final boolean isOwner;
    private final String name;
    private final boolean isJoined;

    @Builder
    private GatheringDetailResponse(
        Gathering gathering,
        boolean isOwner,
        String name,
        boolean isJoined
    ) {
        this.gathering = gathering;
        this.isOwner = isOwner;
        this.name = name;
        this.isJoined = isJoined;
    }

    public static GatheringDetailResponse of(Gathering gathering, boolean isOwner, boolean isJoined) {
        return GatheringDetailResponse.builder()
            .gathering(gathering)
            .isOwner(isOwner)
            .isJoined(isJoined)
            .build();
    }
}
