package com.develop_ping.union.gathering.application.dto;

import com.develop_ping.union.gathering.domain.Gathering;
import com.develop_ping.union.gathering.domain.Place;
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

    @Builder
    private GatheringCommand(
        String title,
        String content,
        Integer maxMember,
        Integer currentMember,
        String address,
        Double latitude,
        Double longitude,
        ZonedDateTime gatheringDateTime
    ) {
        this.title = title;
        this.content = content;
        this.maxMember = maxMember;
        this.currentMember = currentMember;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.gatheringDateTime = gatheringDateTime;
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
                        .build())
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
            '}';
    }
}
