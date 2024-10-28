package com.develop_ping.union.gathering.domain.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.ZonedDateTime;

@Getter
public class GatheringDetailInfo {

    private final Long id;
    private final String title;
    private final String content;
    private final Integer maxMember;
    private final Integer currentMember;
    private final String address;
    private final Double latitude;
    private final Double longitude;
    private final String eupMyeonDong;
    private final ZonedDateTime gatheringDateTime;
    private final Long views;
    private final ZonedDateTime createdAt;
    private final String userNickname;
    private final Long likes;
    private final boolean isOwner;

    @Builder
    public GatheringDetailInfo(
        GatheringInfo gatheringInfo,
        String userNickname,
        Long likes,
        boolean isOwner
    ) {
        this.id = gatheringInfo.getId();
        this.title = gatheringInfo.getTitle();
        this.content = gatheringInfo.getContent();
        this.maxMember = gatheringInfo.getMaxMember();
        this.currentMember = gatheringInfo.getCurrentMember();
        this.address = gatheringInfo.getAddress();
        this.latitude = gatheringInfo.getLatitude();
        this.longitude = gatheringInfo.getLongitude();
        this.gatheringDateTime = gatheringInfo.getGatheringDateTime();
        this.views = gatheringInfo.getViews();
        this.createdAt = gatheringInfo.getCreatedAt();
        this.eupMyeonDong = gatheringInfo.getEupMyeonDong();
        this.userNickname = userNickname;
        this.likes = likes;
        this.isOwner = isOwner;
    }
}
