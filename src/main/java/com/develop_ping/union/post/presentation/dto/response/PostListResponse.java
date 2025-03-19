package com.develop_ping.union.post.presentation.dto.response;

import com.develop_ping.union.post.domain.dto.info.PostListInfo;
import com.develop_ping.union.post.domain.entity.PostType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostListResponse {
    private Long id;
    private PostType type;
    private String title;
    private String contentPreview;
    private String thumbnail;
    private ZonedDateTime createdAt;
    private AuthorResponse author;
    private Integer views;
    private long postLikes;
    private long commentCount;

    @Builder
    public PostListResponse(Long id,
                            PostType type,
                            String title,
                            String contentPreview,
                            String thumbnail,
                            ZonedDateTime createdAt,
                            AuthorResponse author,
                            Integer views,
                            long postLikes,
                            long commentCount) {
        this.id = id;
        this.type = type;
        this.title = title;
        this.contentPreview = contentPreview;
        this.thumbnail = thumbnail;
        this.createdAt = createdAt;
        this.author = author;
        this.views = views;
        this.postLikes = postLikes;
        this.commentCount = commentCount;
    }

    public static PostListResponse from(PostListInfo info) {
        return PostListResponse.builder()
                .id(info.getId())
                .type(info.getType())
                .title(info.getTitle())
                .contentPreview(info.getContentPreview())
                .thumbnail(info.getThumbnail())
                .createdAt(info.getCreatedAt())
                .author(AuthorResponse.from(info))
                .views(info.getViews())
                .postLikes(info.getPostLikes())
                .commentCount(info.getCommentCount())
                .build();
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class AuthorResponse {
        private String nickname;
        private String profileImage;
        private String univName;

        @Builder
        private AuthorResponse(String nickname,
                               String profileImage,
                               String univName) {
            this.nickname = nickname;
            this.profileImage = profileImage;
            this.univName = univName;
        }

        public static AuthorResponse from(PostListInfo info) {
            return AuthorResponse.builder()
                    .nickname(info.getNickname())
                    .profileImage(info.getProfileImage())
                    .univName(info.getUnivName())
                    .build();
        }
    }
}
