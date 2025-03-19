package com.develop_ping.union.gathering.domain.dto.request;

import com.develop_ping.union.gathering.domain.entity.Gathering;
import com.develop_ping.union.gathering.domain.entity.Place;
import lombok.Builder;

import java.time.ZonedDateTime;

public class GatheringCommand {

    private final String title;
    private final String content;
    private final Integer maxMember;
    private final Integer currentMember;
    private final String address;
    private final Double latitude;
    private final Double longitude;
    private final ZonedDateTime gatheringDateTime;
    private final String eupMyeonDong;
    private final String thumbnail;

    @Builder
    private GatheringCommand(
        String title,
        String content,
        Integer maxMember,
        Integer currentMember,
        String address,
        Double latitude,
        Double longitude,
        ZonedDateTime gatheringDateTime,
        String eupMyeonDong,
        String thumbnail
    ) {
        this.title = title;
        this.content = content;
        this.maxMember = maxMember;
        this.currentMember = currentMember;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.gatheringDateTime = gatheringDateTime;
        this.eupMyeonDong = eupMyeonDong;
        this.thumbnail = thumbnail;
    }

    public Gathering toEntity() {
        return Gathering.builder()
            .title(title)
            .content(content)
            .maxMember(maxMember)
            .currentMember(currentMember)
            .gatheringDateTime(gatheringDateTime)
            .place(Place.builder()
                        .address(address)
                        .latitude(latitude)
                        .longitude(longitude)
                        .eupMyeonDong(eupMyeonDong)
                        .build())
            .thumbnail(thumbnail)
            .build();
    }

    @Override
    public String toString() {
        return "GatheringCommand{" +
            "address='" + address + '\'' +
            ", title='" + title + '\'' +
            ", content='" + content + '\'' +
            ", maxMember=" + maxMember +
            ", currentMember=" + currentMember +
            ", latitude=" + latitude +
            ", longitude=" + longitude +
            ", gatheringDateTime=" + gatheringDateTime +
            ", eupMyeonDong= " + eupMyeonDong +
            ", thumbnail='" + thumbnail + '\'' +
            '}';
    }
}
