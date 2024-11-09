package com.develop_ping.union.gathering.presentation.dto.response;

import com.develop_ping.union.gathering.domain.dto.response.GatheringHotListInfo;
import com.develop_ping.union.gathering.domain.dto.response.GatheringListInfo;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Getter
public class GatheringHotListResponse {

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
    private final AuthorResponse author;
    private final String thumbnail;
    private final Long likes;

    @Builder
    private GatheringHotListResponse(
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
        AuthorResponse author,
        String thumbnail,
        Long likes
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
        this.author = author;
        this.thumbnail = thumbnail;
        this.likes = likes;
    }

    public static GatheringHotListResponse from(GatheringHotListInfo gatheringHotListInfo) {
        return GatheringHotListResponse.builder()
                                       .id(gatheringHotListInfo.getId())
                                       .title(gatheringHotListInfo.getTitle())
                                       .content(gatheringHotListInfo.getContent())
                                       .maxMember(gatheringHotListInfo.getMaxMember())
                                       .currentMember(gatheringHotListInfo.getCurrentMember())
                                       .gatheringDateTime(gatheringHotListInfo.getGatheringDateTime())
                                       .views(gatheringHotListInfo.getViews())
                                       .latitude(gatheringHotListInfo.getPlace() != null ? gatheringHotListInfo.getPlace().getLatitude() : null)
                                       .longitude(gatheringHotListInfo.getPlace() != null ? gatheringHotListInfo.getPlace().getLongitude() : null)
                                       .eupMyeonDong(gatheringHotListInfo.getPlace() != null ? gatheringHotListInfo.getPlace().getEupMyeonDong() : null)
                                       .author(AuthorResponse.from(gatheringHotListInfo))
                                       .thumbnail(gatheringHotListInfo.getThumbnail())
                                       .likes(gatheringHotListInfo.getLikes())
                                       .build();
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class AuthorResponse {
        private String token;
        private String nickname;
        private String profileImage;
        private String univName;

        @Builder
        private AuthorResponse(String token,
                               String nickname,
                               String profileImage,
                               String univName) {
            this.token = token;
            this.nickname = nickname;
            this.profileImage = profileImage;
            this.univName = univName;
        }

        public static AuthorResponse from(GatheringHotListInfo info) {
            return AuthorResponse.builder()
                                 .token(info.getUser().getToken())
                                 .nickname(info.getUser().getNickname())
                                 .profileImage(info.getUser().getProfileImage())
                                 .univName(info.getUser().getUnivName())
                                 .build();
        }
    }
}
