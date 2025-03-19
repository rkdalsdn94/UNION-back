package com.develop_ping.union.gathering.domain.dto.response;

import com.develop_ping.union.gathering.domain.entity.Gathering;
import com.develop_ping.union.gathering.domain.entity.Place;
import lombok.Builder;
import lombok.Getter;

import java.time.ZonedDateTime;
import java.util.Optional;

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
    private final String eupMyeonDong;
    private final ZonedDateTime gatheringDateTime;
    private final Long views;
    private final ZonedDateTime createdAt;
    private final boolean recruited;

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
        String eupMyeonDong,
        ZonedDateTime gatheringDateTime,
        Long views,
        ZonedDateTime createdAt,
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
        this.eupMyeonDong = eupMyeonDong;
        this.gatheringDateTime = gatheringDateTime;
        this.views = views;
        this.createdAt = createdAt;
        this.recruited = recruited;
    }

    public static GatheringInfo of(Gathering gathering) {
        return GatheringInfo.builder()
                            .id(gathering.getId())
                            .title(gathering.getTitle())
                            .content(gathering.getContent())
                            .maxMember(gathering.getMaxMember())
                            .currentMember(gathering.getCurrentMember())
                            .address(Optional.ofNullable(gathering.getPlace()).map(Place::getAddress).orElse(null))
                            .latitude(Optional.ofNullable(gathering.getPlace()).map(Place::getLatitude).orElse(null))
                            .longitude(Optional.ofNullable(gathering.getPlace()).map(Place::getLongitude).orElse(null))
                            .eupMyeonDong(Optional.ofNullable(gathering.getPlace()).map(Place::getEupMyeonDong).orElse(null))
                            .gatheringDateTime(gathering.getGatheringDateTime())
                            .views(gathering.getViews())
                            .createdAt(gathering.getCreatedAt())
                            .recruited(gathering.getRecruited())
                            .build();
    }
}
