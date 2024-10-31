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
    private Integer views;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;
    private AuthorResponse author;


    @Builder
    public PostDetailResponse(Long id,
                              String title,
                              String content,
                              PostType type,
                              Integer views,
                              ZonedDateTime createdAt,
                              ZonedDateTime updatedAt,
                              AuthorResponse author) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.type = type;
        this.views = views;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.author = author;
    }

    public static PostDetailResponse from(PostInfo postInfo) {
        return PostDetailResponse.builder()
                .id(postInfo.getId())
                .title(postInfo.getTitle())
                .content(postInfo.getContent())
                .type(postInfo.getType())
                .views(postInfo.getViews())
                .createdAt(postInfo.getCreatedAt())
                .updatedAt(postInfo.getUpdatedAt())
                .author(AuthorResponse.from(postInfo))
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

        public static AuthorResponse from(PostInfo info) {
            return AuthorResponse.builder()
                    .token(info.getToken())
                    .nickname(info.getNickname())
                    .profileImage(info.getProfileImage())
                    .univName(info.getUnivName())
                    .build();
        }
    }
}
