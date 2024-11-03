package com.develop_ping.union.gathering.domain.dto.response;

import com.develop_ping.union.user.domain.entity.User;
import lombok.Builder;
import lombok.Getter;

@Getter
public class GatheringDetailInfo {

    private final GatheringInfo gatheringInfo;
    private final User user;
    private final Long likes;
    private final boolean isOwner;

    @Builder
    private GatheringDetailInfo(
        GatheringInfo gatheringInfo,
        User user,
        Long likes,
        boolean isOwner
    ) {
        this.gatheringInfo = gatheringInfo;
        this.user = user;
        this.likes = likes;
        this.isOwner = isOwner;
    }

    public static GatheringDetailInfo of(GatheringInfo gatheringInfo, User user, Long likes, boolean isOwner) {
        return GatheringDetailInfo.builder()
                                  .gatheringInfo(gatheringInfo)
                                  .user(user)
                                  .likes(likes)
                                  .isOwner(isOwner)
                                  .build();
    }
}
