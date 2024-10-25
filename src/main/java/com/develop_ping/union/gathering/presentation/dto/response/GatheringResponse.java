package com.develop_ping.union.gathering.presentation.dto.response;

import com.develop_ping.union.gathering.domain.dto.GatheringInfo;
import com.develop_ping.union.gathering.domain.entity.Gathering;
import lombok.Builder;
import lombok.Getter;

import java.time.ZonedDateTime;

@Getter
public class GatheringResponse {

    private final Long id;
    private final String title;
    private final String content;
    private final Integer maxMember;
    private final Integer currentMember;
    private final String address;
    private final Double latitude;
    private final Double longitude;
    private final ZonedDateTime gatheringDateTime;
    private final Long views;

    @Builder
    private GatheringResponse(
        Long id,
        String title,
        String content,
        Integer maxMember,
        Integer currentMember,
        String address,
        Double latitude,
        Double longitude,
        ZonedDateTime gatheringDateTime,
        Long views
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
    }

    public static GatheringResponse of(GatheringInfo gathering) {
        return GatheringResponse.builder()
                                .id(gathering.getId())
                                .title(gathering.getTitle())
                                .content(gathering.getContent())
                                .maxMember(gathering.getMaxMember())
                                .currentMember(gathering.getCurrentMember())
                                .address(gathering.getAddress())
                                .latitude(gathering.getLatitude())
                                .longitude(gathering.getLongitude())
                                .gatheringDateTime(gathering.getGatheringDateTime())
                                .views(gathering.getViews())
                                .build();
    }
}
