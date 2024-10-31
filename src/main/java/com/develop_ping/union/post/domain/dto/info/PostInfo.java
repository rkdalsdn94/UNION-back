package com.develop_ping.union.post.domain.dto.info;

import com.develop_ping.union.post.domain.entity.Post;
import com.develop_ping.union.post.domain.entity.PostType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostInfo {
    private Long id;
    private String title;
    private String content;
    private PostType type;
    private String thumbnail;
    private Integer views;
    private String token;
    private String nickname;
    private String profileImage;
    private String univName;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;

    @Builder
    public PostInfo(Long id,
                    String title,
                    String content,
                    PostType type,
                    String thumbnail,
                    Integer views,
                    String token,
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
        this.token = token;
        this.nickname = nickname;
        this.profileImage = profileImage;
        this.univName = univName;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static PostInfo from(Post post) {
        return PostInfo.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .type(post.getType())
                .thumbnail(post.getThumbnail())
                .views(post.getViews())
                .token(post.getUser().getToken())
                .nickname(post.getUser().getNickname())
                .profileImage(post.getUser().getProfileImage())
                .univName(post.getUser().getUnivName())
                .createdAt(post.getCreatedAt())
                .updatedAt(post.getUpdatedAt())
                .build();
    }
}
