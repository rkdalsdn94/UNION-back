package com.develop_ping.union.gathering.presentation.dto.response;

import com.develop_ping.union.gathering.domain.entity.Gathering;
import java.time.ZonedDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Slice;

@Getter
public class GatheringListResponse {

    private final Long id;
    private final String title;
    private final String content;
    private final Integer maxMember;
    private final Integer currentMember;
    private final String eupMyeonDong;
    private final ZonedDateTime gatheringDateTime;
    private final Long views;
    private final Double latitude;
    private final Double longitude;
    private final String name;
    private final ZonedDateTime createdAt;

    @Builder
    private GatheringListResponse(
        Long id,
        String title,
        String content,
        Integer maxMember,
        Integer currentMember,
        String eupMyeonDong,
        ZonedDateTime gatheringDateTime,
        Long views,
        Double latitude,
        Double longitude,
        String name,
        ZonedDateTime createdAt
    ) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.maxMember = maxMember;
        this.currentMember = currentMember;
        this.eupMyeonDong = eupMyeonDong;
        this.gatheringDateTime = gatheringDateTime;
        this.views = views;
        this.latitude = latitude;
        this.longitude = longitude;
        this.name = name;
        this.createdAt = createdAt;
    }

    public static GatheringListResponse from(Gathering gathering, String name) {
        return getBuild(gathering, name);
    }

    public static Slice<GatheringListResponse> fromSlice(Slice<Gathering> gatherings, String name) {
        return gatherings.map(gathering -> getBuild(gathering, name));
    }

    public static List<GatheringListResponse> fromList(List<Gathering> gatherings, String name) {
        return gatherings.stream()
            .map(gathering -> getBuild(gathering, name))
            .toList();
    }

    private static GatheringListResponse getBuild(Gathering gathering, String name) {
        return GatheringListResponse.builder()
            .id(gathering.getId())
            .title(gathering.getTitle())
            .content(gathering.getContent())
            .maxMember(gathering.getMaxMember())
            .currentMember(gathering.getCurrentMember())
            .gatheringDateTime(gathering.getGatheringDateTime())
            .views(gathering.getViews())
            .latitude(gathering.getPlace() != null ? gathering.getPlace().getLatitude() : null)
            .longitude(gathering.getPlace() != null ? gathering.getPlace().getLongitude() : null)
            .eupMyeonDong(
                gathering.getPlace() != null ? gathering.getPlace().getEupMyeonDong() : null)
            .name(name)
            .createdAt(gathering.getCreatedAt())
            .build();
    }
}
