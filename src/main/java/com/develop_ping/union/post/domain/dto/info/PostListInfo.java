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
public class PostListInfo {
    private Long id;
    private PostType type;
    private String title;
    private String contentPreview;
    private String thumbnail;
    private String nickname;
    private String profileImage;
    private String univName;
    private ZonedDateTime createdAt;
    private Integer views;
    private long postLikes;
    private long commentCount;

    @Builder
    private PostListInfo(Long id,
                         PostType type,
                         String title,
                         String contentPreview,
                         String thumbnail,
                         String nickname,
                         String profileImage,
                         String univName,
                         ZonedDateTime createdAt,
                         Integer views,
                         long postLikes,
                         long commentCount) {
        this.id = id;
        this.type = type;
        this.title = title;
        this.contentPreview = contentPreview;
        this.thumbnail = thumbnail;
        this.nickname = nickname;
        this.profileImage = profileImage;
        this.univName = univName;
        this.createdAt = createdAt;
        this.views = views;
        this.postLikes = postLikes;
        this.commentCount = commentCount;
    }

    public static PostListInfo of(Post post, long postLikes, long commentCount) {
        // content가 20자를 초과할 경우 '...' 추가
        String contentPreview = post.getContent().length() > 20
                ? post.getContent().substring(0, 20) + "..."
                : post.getContent();

        return PostListInfo.builder()
                .id(post.getId())
                .type(post.getType())
                .title(post.getTitle())
                .contentPreview(contentPreview)
                .thumbnail(post.getThumbnail())
                .nickname(post.getUser().getNickname())
                .profileImage(post.getUser().getProfileImage())
                .univName(post.getUser().getUnivName())
                .createdAt(post.getCreatedAt())
                .views(post.getViews())
                .postLikes(postLikes)
                .commentCount(commentCount)
                .build();
    }
}
