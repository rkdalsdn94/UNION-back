package com.develop_ping.union.user.domain.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UserStatInfo {
    private final long postCount;
    private final long commentCount;
    private final long gatheringCount;

    @Builder
    private UserStatInfo(long postCount, long commentCount, long gatheringCount) {
        this.postCount = postCount;
        this.commentCount = commentCount;
        this.gatheringCount = gatheringCount;
    }

    public static UserStatInfo of(long postCount, long commentCount, long gatheringCount) {
        return UserStatInfo.builder()
                .postCount(postCount)
                .commentCount(commentCount)
                .gatheringCount(gatheringCount)
                .build();
    }
}
