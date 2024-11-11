package com.develop_ping.union.gathering.domain.dto.response;

import com.develop_ping.union.gathering.domain.entity.Gathering;
import com.develop_ping.union.gathering.domain.entity.Place;
import com.develop_ping.union.gathering.infra.response.GatheringWithLikes;
import com.develop_ping.union.user.domain.entity.User;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Slice;

import java.time.ZonedDateTime;
import java.util.List;

@Getter
public class GatheringHotListInfo {

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
    private final Long likes;
    private final ZonedDateTime createdAt;

    @Builder
    private GatheringHotListInfo(
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
        Long likes,
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
        this.likes = likes;
        this.createdAt = createdAt;
    }

    public static GatheringHotListInfo from(GatheringWithLikes gatheringWithLikes) {
        Gathering gathering = gatheringWithLikes.getGathering();
        Long likes = gatheringWithLikes.getLikes();

        return GatheringHotListInfo.builder()
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
                                   .likes(likes)
                                   .createdAt(gathering.getCreatedAt())
                                   .build();
    }
}
