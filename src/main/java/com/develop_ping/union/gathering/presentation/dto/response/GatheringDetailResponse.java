package com.develop_ping.union.gathering.presentation.dto.response;

import com.develop_ping.union.gathering.domain.dto.response.GatheringDetailInfo;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Getter
public class GatheringDetailResponse {

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
    private final String userNickname;
    private final ZonedDateTime createdAt;
    private final Long likes;
    private final Long views;
    private final boolean isOwner;
    private final AuthorResponse author;

    @Builder
    private GatheringDetailResponse(
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
        String userNickname,
        Long likes,
        boolean isOwner,
        AuthorResponse author
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
        this.userNickname = userNickname;
        this.likes = likes;
        this.isOwner = isOwner;
        this.author = author;
    }

    public static GatheringDetailResponse from(GatheringDetailInfo gatheringInfo) {
        return GatheringDetailResponse.builder()
                                      .id(gatheringInfo.getGatheringInfo().getId())
                                      .title(gatheringInfo.getGatheringInfo().getTitle())
                                      .content(gatheringInfo.getGatheringInfo().getContent())
                                      .maxMember(gatheringInfo.getGatheringInfo().getMaxMember())
                                      .currentMember(gatheringInfo.getGatheringInfo().getCurrentMember())
                                      .address(gatheringInfo.getGatheringInfo().getAddress())
                                      .latitude(gatheringInfo.getGatheringInfo().getLatitude())
                                      .longitude(gatheringInfo.getGatheringInfo().getLongitude())
                                      .eupMyeonDong(gatheringInfo.getGatheringInfo().getEupMyeonDong())
                                      .gatheringDateTime(gatheringInfo.getGatheringInfo().getGatheringDateTime())
                                      .views(gatheringInfo.getGatheringInfo().getViews())
                                      .createdAt(gatheringInfo.getGatheringInfo().getCreatedAt())
                                      .userNickname(gatheringInfo.getUser().getNickname())
                                      .likes(gatheringInfo.getLikes())
                                      .isOwner(gatheringInfo.isOwner())
                                      .author(AuthorResponse.from(gatheringInfo))
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

        public static GatheringDetailResponse.AuthorResponse from(GatheringDetailInfo info) {
            return AuthorResponse.builder()
                                 .token(info.getUser().getToken())
                                 .nickname(info.getUser().getUnivName())
                                 .profileImage(info.getUser().getProfileImage())
                                 .univName(info.getUser().getUnivName())
                                 .build();
        }
    }
}
