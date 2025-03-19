package com.develop_ping.union.gathering.domain.dto.response;

import com.develop_ping.union.gathering.domain.entity.Gathering;
import com.develop_ping.union.gathering.domain.entity.Place;
import com.develop_ping.union.user.domain.entity.User;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Slice;

import java.time.ZonedDateTime;
import java.util.List;

@Getter
public class GatheringListInfo {

    private final Long id;
    private final String title;
    private final String content;
    private final Integer maxMember;
    private final Integer currentMember;
    private final ZonedDateTime gatheringDateTime;
    private final Long views;
    private final String thumbnail;
    private final Place place;
    private final User user;
    private final ZonedDateTime createdAt;

    @Builder
    private GatheringListInfo(
        Long id,
        String title,
        String content,
        Integer maxMember,
        Integer currentMember,
        ZonedDateTime gatheringDateTime,
        Long views,
        Place place,
        User user,
        String thumbnail,
        ZonedDateTime createdAt
    ) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.maxMember = maxMember;
        this.currentMember = currentMember;
        this.gatheringDateTime = gatheringDateTime;
        this.views = views;
        this.place = place;
        this.user = user;
        this.thumbnail = thumbnail;
        this.createdAt = createdAt;
    }

    public static GatheringListInfo from(Gathering gathering, User user) {
        return GatheringListInfo.builder()
                                .id(gathering.getId())
                                .title(gathering.getTitle())
                                .content(gathering.getContent())
                                .maxMember(gathering.getMaxMember())
                                .currentMember(gathering.getCurrentMember())
                                .gatheringDateTime(gathering.getGatheringDateTime())
                                .views(gathering.getViews())
                                .place(gathering.getPlace())
                                .user(user)
                                .thumbnail(gathering.getThumbnail())
                                .createdAt(gathering.getCreatedAt())
                                .build();
    }

    public static Slice<GatheringListInfo> fromSlice(Slice<Gathering> gatherings) {
        return gatherings.map(gathering -> GatheringListInfo.builder()
                                                            .id(gathering.getId())
                                                            .title(gathering.getTitle())
                                                            .content(gathering.getContent())
                                                            .maxMember(gathering.getMaxMember())
                                                            .currentMember(gathering.getCurrentMember())
                                                            .gatheringDateTime(gathering.getGatheringDateTime())
                                                            .views(gathering.getViews())
                                                            .place(gathering.getPlace())
                                                            .user(gathering.getOwner())
                                                            .thumbnail(gathering.getThumbnail())
                                                            .createdAt(gathering.getCreatedAt())
                                                            .build());
    }

    public static List<GatheringListInfo> fromList(List<Gathering> gatherings) {
        return gatherings.stream()
            .map(gathering -> GatheringListInfo.builder()
                                               .id(gathering.getId())
                                               .title(gathering.getTitle())
                                               .content(gathering.getContent())
                                               .maxMember(gathering.getMaxMember())
                                               .currentMember(gathering.getCurrentMember())
                                               .gatheringDateTime(gathering.getGatheringDateTime())
                                               .views(gathering.getViews())
                                               .place(gathering.getPlace())
                                               .user(gathering.getUser())
                                               .thumbnail(gathering.getThumbnail())
                                               .createdAt(gathering.getCreatedAt())
                                               .build())
            .toList();
    }
}
