package com.develop_ping.union.gathering.domain.dto.response;

import com.develop_ping.union.gathering.domain.entity.Gathering;
import com.develop_ping.union.gathering.domain.entity.Place;
import com.develop_ping.union.user.domain.entity.User;
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
    private final ZonedDateTime gatheringDateTime;
    private final Long views;
    private final String thumbnail;
    private final Place place;
    private final User user;

    @Builder
    private GatheringListInfo(
        Long id,
        String title,
        Integer maxMember,
        Integer currentMember,
        ZonedDateTime gatheringDateTime,
        Long views,
        Place place,
        User user,
        String thumbnail
    ) {
        this.id = id;
        this.title = title;
        this.maxMember = maxMember;
        this.currentMember = currentMember;
        this.gatheringDateTime = gatheringDateTime;
        this.views = views;
        this.place = place;
        this.user = user;
        this.thumbnail = thumbnail;
    }

    public static GatheringListInfo from(Gathering gathering, User user) {
        return GatheringListInfo.builder()
                                .id(gathering.getId())
                                .title(gathering.getTitle())
                                .maxMember(gathering.getMaxMember())
                                .currentMember(gathering.getCurrentMember())
                                .gatheringDateTime(gathering.getGatheringDateTime())
                                .views(gathering.getViews())
                                .place(gathering.getPlace())
                                .user(user)
                                .thumbnail(gathering.getThumbnail())
                                .build();
    }

    // Slice<Gathering>을 받아 Slice<GatheringListInfo>로 변환하는 메서드
    public static Slice<GatheringListInfo> of(Slice<Gathering> gatherings) {
        return gatherings.map(gathering -> GatheringListInfo.builder()
                                                            .id(gathering.getId())
                                                            .title(gathering.getTitle())
                                                            .maxMember(gathering.getMaxMember())
                                                            .currentMember(gathering.getCurrentMember())
                                                            .gatheringDateTime(gathering.getGatheringDateTime())
                                                            .views(gathering.getViews())
                                                            .place(gathering.getPlace())
                                                            .user(gathering.getOwner())
                                                            .thumbnail(gathering.getThumbnail())
                                                            .build());
    }
}
