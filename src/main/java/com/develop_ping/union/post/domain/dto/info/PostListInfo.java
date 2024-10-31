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
    private String title;
    private String contentPreview;
    private PostType type;
    private String thumbnail;
    private String nickname;
    private String profileImage;
    private String univName;
    private ZonedDateTime createdAt;
    private Integer views;
    private Integer postLikes;
    private Integer CommentCount;

    @Builder
    private PostListInfo(Long id,
                        String title,
                        String contentPreview,
                        PostType type,
                        String thumbnail,
                        String nickname,
                        String profileImage,
                        String univName,
                        ZonedDateTime createdAt,
                        Integer views,
                        Integer postLikes,
                        Integer CommentCount) {
        this.id = id;
        this.title = title;
        this.contentPreview = contentPreview;
        this.type = type;
        this.thumbnail = thumbnail;
        this.nickname = nickname;
        this.profileImage = profileImage;
        this.univName = univName;
        this.createdAt = createdAt;
        this.views = views;
        this.postLikes = postLikes;
        this.CommentCount = CommentCount;
    }

    public static PostListInfo from(Post post) {
        // content가 20자를 초과할 경우 '...' 추가
        String contentPreview = post.getContent().length() > 20
                ? post.getContent().substring(0, 20) + "..."
                : post.getContent();

        // TODO: postLikes, CommentCount 추가
        return PostListInfo.builder()
                .id(post.getId())
                .title(post.getTitle())
                .contentPreview(contentPreview)
                .thumbnail(post.getThumbnail())
                .nickname(post.getUser().getNickname())
                .profileImage(post.getUser().getProfileImage())
                .univName(post.getUser().getUnivName())
                .createdAt(post.getCreatedAt())
                .views(post.getViews())
                .postLikes(0)
                .CommentCount(0)
                .build();
    }
}
