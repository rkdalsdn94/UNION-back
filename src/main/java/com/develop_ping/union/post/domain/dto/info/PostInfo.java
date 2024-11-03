package com.develop_ping.union.post.domain.dto.info;

import com.develop_ping.union.post.domain.entity.Post;
import com.develop_ping.union.post.domain.entity.PostType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;
import java.util.List;

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
    private List<String> photos;

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
                    List<String> photos
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
        this.photos = photos;
    }

    public static PostInfo from(Post post) {
        return PostInfo.builder()
                .id(post.getId())
                .build();
    }

    public static PostInfo of(Post post, List<String> photos) {
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
                .photos(photos)
                .build();
    }
}
