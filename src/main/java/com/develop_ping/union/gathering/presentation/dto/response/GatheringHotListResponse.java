package com.develop_ping.union.gathering.presentation.dto.response;

import com.develop_ping.union.gathering.domain.entity.Place;
import java.time.ZonedDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
public class GatheringHotListResponse {

    private final Long id;
    private final String title;
    private final String content;
    private final Integer maxMember;
    private final Integer currentMember;
    private final ZonedDateTime gatheringDateTime;
    private final Long views;
    private final String thumbnail;
    private final Long likes;
    private final ZonedDateTime createdAt;
    private final String name;
    private final Place place;

    @Builder
    private GatheringHotListResponse(
        Long id,
        String title,
        String content,
        Integer maxMember,
        Integer currentMember,
        ZonedDateTime gatheringDateTime,
        Long views,
        Place place,
        String name,
        String thumbnail,
        Long likes,
        ZonedDateTime createdAt
    ) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.maxMember = maxMember;
        this.currentMember = currentMember;
        this.place = place;
        this.gatheringDateTime = gatheringDateTime;
        this.views = views;
        this.name = name;
        this.thumbnail = thumbnail;
        this.likes = likes;
        this.createdAt = createdAt;
    }

    public static GatheringHotListResponse from(GatheringHotListResponse gatheringHotListResponse) {
        return GatheringHotListResponse.builder()
            .id(gatheringHotListResponse.getId())
            .title(gatheringHotListResponse.getTitle())
            .content(gatheringHotListResponse.getContent())
            .maxMember(gatheringHotListResponse.getMaxMember())
            .currentMember(gatheringHotListResponse.getCurrentMember())
            .gatheringDateTime(gatheringHotListResponse.getGatheringDateTime())
            .views(gatheringHotListResponse.getViews())
            .place(gatheringHotListResponse.getPlace())
            .name(gatheringHotListResponse.getName())
            .thumbnail(gatheringHotListResponse.getThumbnail())
            .likes(gatheringHotListResponse.getLikes())
            .createdAt(gatheringHotListResponse.getCreatedAt())
            .build();
    }
}
