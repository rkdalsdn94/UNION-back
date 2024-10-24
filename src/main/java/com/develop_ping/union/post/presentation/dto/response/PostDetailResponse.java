package com.develop_ping.union.post.presentation.dto.response;

import com.develop_ping.union.post.domain.dto.info.PostInfo;
import com.develop_ping.union.post.domain.entity.PostType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class PostDetailResponse {
    private Long id;
    private String title;
    private String content;
    private PostType type;
    private String thumbnail;
    private Integer views;
    private String nickname;
    private String profileImage;
    private String univName;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;


    @Builder
    public PostDetailResponse(Long id,
                              String title,
                              String content,
                              PostType type,
                              String thumbnail,
                              Integer views,
                              String nickname,
                              String profileImage,
                              String univName,
                              ZonedDateTime createdAt,
                              ZonedDateTime updatedAt
    ) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.type = type;
        this.thumbnail = thumbnail;
        this.views = views;
        this.nickname = nickname;
        this.profileImage = profileImage;
        this.univName = univName;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static PostDetailResponse from(PostInfo postInfo) {
        return PostDetailResponse.builder()
                .id(postInfo.getId())
                .title(postInfo.getTitle())
                .content(postInfo.getContent())
                .type(postInfo.getType())
                .thumbnail(postInfo.getThumbnail())
                .views(postInfo.getViews())
                .nickname(postInfo.getNickname())
                .profileImage(postInfo.getProfileImage())
                .univName(postInfo.getUnivName())
                .createdAt(postInfo.getCreatedAt())
                .updatedAt(postInfo.getUpdatedAt())
                .build();
    }
}
