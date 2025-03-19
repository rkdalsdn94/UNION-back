package com.develop_ping.union.gathering.presentation.dto.response;

import com.develop_ping.union.gathering.domain.entity.Gathering;
import java.time.ZonedDateTime;
import lombok.Builder;
import lombok.Getter;

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
    private final boolean recruited;

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
        boolean recruited
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
        this.recruited = recruited;
    }

    public static GatheringResponse of(Gathering gathering) {
        return GatheringResponse.builder()
                                .id(gathering.getId())
                                .title(gathering.getTitle())
                                .content(gathering.getContent())
                                .maxMember(gathering.getMaxMember())
                                .currentMember(gathering.getCurrentMember())
                                .address(gathering.getPlace().getAddress())
                                .latitude(gathering.getPlace().getLatitude())
                                .longitude(gathering.getPlace().getLongitude())
                                .gatheringDateTime(gathering.getGatheringDateTime())
                                .recruited(gathering.getRecruited())
                                .build();
    }
}
