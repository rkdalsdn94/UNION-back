package com.develop_ping.union.gathering.presentation.dto.response;

import com.develop_ping.union.gathering.domain.dto.GatheringDetailInfo;
import com.develop_ping.union.gathering.domain.dto.GatheringInfo;
import lombok.Builder;
import lombok.Getter;

import java.time.ZonedDateTime;

@Getter
public class GatheringListResponse {

    private final Long id;
    private final String title;
    private final String content;
    private final Integer maxMember;
    private final Integer currentMember;
    private final String address;
    private final Double latitude;
    private final Double longitude;
    private final ZonedDateTime gatheringDateTime;
    private final String userNickname;
    private final ZonedDateTime createdAt;
    private final Long likes;
    private final Long views;
    private final boolean isOwner;

    @Builder
    private GatheringListResponse(
        Long id,
        String title,
        String content,
        Integer maxMember,
        Integer currentMember,
        String address,
        Double latitude,
        Double longitude,
        ZonedDateTime gatheringDateTime,
        Long views,
        ZonedDateTime createdAt,
        String userNickname,
        Long likes,
        boolean isOwner
    ) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.maxMember = maxMember;
        this.currentMember = currentMember;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.gatheringDateTime = gatheringDateTime;
        this.views = views;
        this.createdAt = createdAt;
        this.userNickname = userNickname;
        this.likes = likes;
        this.isOwner = isOwner;
    }

    public static GatheringListResponse of(GatheringDetailInfo gatheringInfo) {
        return GatheringListResponse.builder()
                                    .id(gatheringInfo.getId())
                                    .title(gatheringInfo.getTitle())
                                    .content(gatheringInfo.getContent())
                                    .maxMember(gatheringInfo.getMaxMember())
                                    .currentMember(gatheringInfo.getCurrentMember())
                                    .address(gatheringInfo.getAddress())
                                    .latitude(gatheringInfo.getLatitude())
                                    .longitude(gatheringInfo.getLongitude())
                                    .gatheringDateTime(gatheringInfo.getGatheringDateTime())
                                    .views(gatheringInfo.getViews())
                                    .createdAt(gatheringInfo.getCreatedAt())
                                    .userNickname(gatheringInfo.getUserNickname())
                                    .likes(gatheringInfo.getLikes())
                                    .isOwner(gatheringInfo.isOwner())
                                    .build();
    }
}
