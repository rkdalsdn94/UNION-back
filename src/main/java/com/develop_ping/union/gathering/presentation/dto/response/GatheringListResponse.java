package com.develop_ping.union.gathering.presentation.dto.response;

import com.develop_ping.union.gathering.domain.dto.response.GatheringListInfo;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

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
    private final AuthorResponse author;
    private final String thumbnail;

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
        AuthorResponse author,
        String thumbnail
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
    }

    // GatheringListInfo를 GatheringListResponse로 변환하는 정적 메서드
    public static GatheringListResponse from(GatheringListInfo gatheringListInfo) {
        return GatheringListResponse.builder()
                                    .id(gatheringListInfo.getId())
                                    .title(gatheringListInfo.getTitle())
                                    .content(gatheringListInfo.getContent())
                                    .maxMember(gatheringListInfo.getMaxMember())
                                    .currentMember(gatheringListInfo.getCurrentMember())
                                    .gatheringDateTime(gatheringListInfo.getGatheringDateTime())
                                    .views(gatheringListInfo.getViews())
                                    .latitude(gatheringListInfo.getPlace() != null ? gatheringListInfo.getPlace().getLatitude() : null)
                                    .longitude(gatheringListInfo.getPlace() != null ? gatheringListInfo.getPlace().getLongitude() : null)
                                    .eupMyeonDong(gatheringListInfo.getPlace() != null ? gatheringListInfo.getPlace().getEupMyeonDong() : null)
                                    .author(AuthorResponse.from(gatheringListInfo))
                                    .thumbnail(gatheringListInfo.getThumbnail())
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

        public static AuthorResponse from(GatheringListInfo info) {
            return AuthorResponse.builder()
                                 .token(info.getUser().getToken())
                                 .nickname(info.getUser().getNickname())
                                 .profileImage(info.getUser().getProfileImage())
                                 .univName(info.getUser().getUnivName())
                                 .build();
        }
    }
}
