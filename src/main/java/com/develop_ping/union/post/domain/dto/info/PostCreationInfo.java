package com.develop_ping.union.post.domain.dto.info;

import com.develop_ping.union.post.presentation.dto.response.PostCreationResponse;
import com.develop_ping.union.post.domain.entity.Post;
import com.develop_ping.union.post.domain.entity.PostType;
import com.develop_ping.union.user.domain.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostCreationInfo {
    private Long id;
    private String title;
    private String content;
    private PostType type;
    private String thumbnail;
    private Integer views;
    private User user;

    @Builder
    public PostCreationInfo(Long id, String title, String content, PostType type, String thumbnail, Integer views, User user) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.type = type;
        this.thumbnail = thumbnail;
        this.views = views;
        this.user = user;
    }

    public static PostCreationInfo of(Post post) {
        return PostCreationInfo.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .type(post.getType())
                .thumbnail(post.getThumbnail())
                .views(post.getViews())
                .user(post.getUser())
                .build();
    }
}
