package com.develop_ping.union.post.application.dto.info;

import com.develop_ping.union.post.controller.dto.response.PostUpdateResponse;
import com.develop_ping.union.post.domain.Post;
import com.develop_ping.union.post.domain.PostType;
import com.develop_ping.union.user.domain.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostUpdateInfo {
    private Long id;
    private String title;
    private String content;
    private PostType type;
    private String thumbnail;
    private Integer views;
    private User user;

    @Builder
    public PostUpdateInfo(Long id, String title, String content, PostType type, String thumbnail, Integer views, User user) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.type = type;
        this.thumbnail = thumbnail;
        this.views = views;
        this.user = user;
    }

    public static PostUpdateInfo of(Post post) {
        return PostUpdateInfo.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .type(post.getType())
                .thumbnail(post.getThumbnail())
                .views(post.getViews())
                .user(post.getUser())
                .build();
    }

    public PostUpdateResponse toResponse() {
        return PostUpdateResponse.builder()
                .id(this.id)
                .build();
    }
}
