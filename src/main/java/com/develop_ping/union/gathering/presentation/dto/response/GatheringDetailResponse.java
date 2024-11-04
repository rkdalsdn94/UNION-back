package com.develop_ping.union.gathering.presentation.dto.response;

import com.develop_ping.union.gathering.domain.dto.response.GatheringDetailInfo;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;
import java.util.List;

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
    private final boolean isLiked;
    private final AuthorResponse author;
    private final boolean recruited;
    private final List<String> photos;
    private final boolean isJoined;

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
        boolean isLiked,
        AuthorResponse author,
        List<String> photos,
        boolean recruited,
        boolean isJoined
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
        this.isLiked = isLiked;
        this.author = author;
        this.photos = photos;
        this.recruited = recruited;
        this.isJoined = isJoined;
    }

    public static GatheringDetailResponse from(GatheringDetailInfo gatheringDetailInfo) {
        return GatheringDetailResponse.builder()
                                      .id(gatheringDetailInfo.getGatheringInfo().getId())
                                      .title(gatheringDetailInfo.getGatheringInfo().getTitle())
                                      .content(gatheringDetailInfo.getGatheringInfo().getContent())
                                      .maxMember(gatheringDetailInfo.getGatheringInfo().getMaxMember())
                                      .currentMember(gatheringDetailInfo.getGatheringInfo().getCurrentMember())
                                      .address(gatheringDetailInfo.getGatheringInfo().getAddress())
                                      .latitude(gatheringDetailInfo.getGatheringInfo().getLatitude())
                                      .longitude(gatheringDetailInfo.getGatheringInfo().getLongitude())
                                      .eupMyeonDong(gatheringDetailInfo.getGatheringInfo().getEupMyeonDong())
                                      .gatheringDateTime(gatheringDetailInfo.getGatheringInfo().getGatheringDateTime())
                                      .views(gatheringDetailInfo.getGatheringInfo().getViews())
                                      .createdAt(gatheringDetailInfo.getGatheringInfo().getCreatedAt())
                                      .userNickname(gatheringDetailInfo.getUser().getNickname())
                                      .likes(gatheringDetailInfo.getLikes())
                                      .isOwner(gatheringDetailInfo.isOwner())
                                      .isLiked(gatheringDetailInfo.isLiked())
                                      .author(AuthorResponse.from(gatheringDetailInfo))
                                      .photos(gatheringDetailInfo.getPhotos())
                                      .recruited(gatheringDetailInfo.getGatheringInfo().isRecruited())
                                      .isJoined(gatheringDetailInfo.isJoined())
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
