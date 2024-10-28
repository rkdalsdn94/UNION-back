package com.develop_ping.union.gathering.presentation.dto.response;

import com.develop_ping.union.gathering.domain.dto.response.GatheringInfo;
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
        ZonedDateTime gatheringDateTime
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
                                .build();
    }
}
