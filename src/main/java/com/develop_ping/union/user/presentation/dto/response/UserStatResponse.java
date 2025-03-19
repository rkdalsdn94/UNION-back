package com.develop_ping.union.user.presentation.dto.response;

import com.develop_ping.union.user.domain.dto.UserStatInfo;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserStatResponse {
    private long postCount;
    private long commentCount;
    private long gatheringCount;

    @Builder
    private UserStatResponse(long postCount, long commentCount, long gatheringCount) {
        this.postCount = postCount;
        this.commentCount = commentCount;
        this.gatheringCount = gatheringCount;
    }

    public static UserStatResponse from(UserStatInfo info) {
        return UserStatResponse.builder()
                .postCount(info.getPostCount())
                .commentCount(info.getCommentCount())
                .gatheringCount(info.getGatheringCount())
                .build();
    }
}
