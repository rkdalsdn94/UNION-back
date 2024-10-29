package com.develop_ping.union.gathering.presentation.dto.response;

import com.develop_ping.union.gathering.domain.dto.response.GatheringListInfo;
import lombok.Builder;
import lombok.Getter;

import java.time.ZonedDateTime;

@Getter
public class GatheringListResponse {

    private final Long id;
    private final String title;
    private final Integer maxMember;
    private final Integer currentMember;
    private final String eupMyeonDong;
    private final ZonedDateTime gatheringDateTime;
    private final Long views;

    @Builder
    private GatheringListResponse (
        Long id,
        String title,
        Integer maxMember,
        Integer currentMember,
        String eupMyeonDong,
        ZonedDateTime gatheringDateTime,
        Long views
    ) {
        this.id = id;
        this.title = title;
        this.maxMember = maxMember;
        this.currentMember = currentMember;
        this.eupMyeonDong = eupMyeonDong;
        this.gatheringDateTime = gatheringDateTime;
        this.views = views;
    }

    // GatheringListInfo를 GatheringListResponse로 변환하는 정적 메서드
    public static GatheringListResponse from(GatheringListInfo gatheringInfo) {
        return GatheringListResponse.builder()
                                    .id(gatheringInfo.getId())
                                    .title(gatheringInfo.getTitle())
                                    .maxMember(gatheringInfo.getMaxMember())
                                    .currentMember(gatheringInfo.getCurrentMember())
                                    .eupMyeonDong(gatheringInfo.getEupMyeonDong())
                                    .gatheringDateTime(gatheringInfo.getGatheringDateTime())
                                    .views(gatheringInfo.getViews())
                                    .build();
    }
}
