package com.develop_ping.union.gathering.domain.dto;

import com.develop_ping.union.gathering.domain.entity.Gathering;
import lombok.Builder;
import lombok.Getter;

import java.time.ZonedDateTime;

@Getter
public class GatheringInfo {

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
    private GatheringInfo(
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

    public static GatheringInfo of(Gathering gathering) {
        return GatheringInfo.builder()
                            .id(gathering.getId())
                            .title(gathering.getTitle())
                            .content(gathering.getContent())
                            .maxMember(gathering.getMaxMember())
                            .currentMember(gathering.getCurrentMember())
                            .address(gathering.getPlace().getAddress())
                            .latitude(gathering.getPlace().getLatitude())
                            .longitude(gathering.getPlace().getLongitude())
                            .gatheringDateTime(gathering.getGatheringDateTime())
                            .views(gathering.getViews())
                            .build();
    }

    @Override
    public String toString() {
        return "GatheringInfo{" +
            "address='" + address + '\'' +
            ", id=" + id +
            ", title='" + title + '\'' +
            ", content='" + content + '\'' +
            ", maxMember=" + maxMember +
            ", currentMember=" + currentMember +
            ", latitude=" + latitude +
            ", longitude=" + longitude +
            ", gatheringDateTime=" + gatheringDateTime +
            ", views=" + views +
            '}';
    }
}
