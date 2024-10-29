package com.develop_ping.union.gathering.domain.dto.response;

import com.develop_ping.union.gathering.domain.entity.Gathering;
import com.develop_ping.union.gathering.domain.entity.Place;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Slice;

import java.time.ZonedDateTime;
import java.util.Optional;

@Getter
public class GatheringListInfo {

    private final Long id;
    private final String title;
    private final Integer maxMember;
    private final Integer currentMember;
    private final String eupMyeonDong;
    private final ZonedDateTime gatheringDateTime;
    private final Long views;

    @Builder
    private GatheringListInfo (
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

    // Gathering 엔티티를 GatheringListInfo로 변환하는 정적 메서드
    public static GatheringListInfo from(Gathering gathering) {
        return GatheringListInfo.builder()
                                .id(gathering.getId())
                                .title(gathering.getTitle())
                                .maxMember(gathering.getMaxMember())
                                .currentMember(gathering.getCurrentMember())
                                .eupMyeonDong(Optional.ofNullable(gathering.getPlace()).map(Place::getEupMyeonDong).orElse(null))
                                .gatheringDateTime(gathering.getGatheringDateTime())
                                .views(gathering.getViews())
                                .build();
    }

    // Slice<Gathering>을 받아 Slice<GatheringListInfo>로 변환하는 메서드
    public static Slice<GatheringListInfo> of(Slice<Gathering> gatherings) {
        return gatherings.map(GatheringListInfo::from);
    }
}
