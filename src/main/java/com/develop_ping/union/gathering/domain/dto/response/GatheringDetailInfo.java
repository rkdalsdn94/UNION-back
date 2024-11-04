package com.develop_ping.union.gathering.domain.dto.response;

import com.develop_ping.union.user.domain.entity.User;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class GatheringDetailInfo {

    private final GatheringInfo gatheringInfo;
    private final User user;
    private final Long likes;
    private final boolean isOwner;
    private final boolean isLiked;
    private final List<String> photos;

    @Builder
    private GatheringDetailInfo(
        GatheringInfo gatheringInfo,
        User user,
        Long likes,
        boolean isOwner,
        boolean isLiked,
        List<String> photos
    ) {
        this.gatheringInfo = gatheringInfo;
        this.user = user;
        this.likes = likes;
        this.isOwner = isOwner;
        this.isLiked = isLiked;
        this.photos = photos;
    }

    public static GatheringDetailInfo of(GatheringInfo gatheringInfo, User user, Long likes, boolean isOwner) {
        return GatheringDetailInfo.builder()
                                  .gatheringInfo(gatheringInfo)
                                  .user(user)
                                  .likes(likes)
                                  .isLiked(false)
                                  .isOwner(isOwner)
                                  .build();
    }
}
