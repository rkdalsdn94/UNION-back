package com.develop_ping.union.post.domain.dto.command;

import com.develop_ping.union.post.domain.entity.PostType;
import com.develop_ping.union.user.domain.entity.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostCreationCommand {
    private User user;
    private String title;
    private String content;
    private String thumbnail;
    private PostType postType;

    @Builder
    public PostCreationCommand(User user, String title, String content, String thumbnail, PostType postType) {
        this.user = user;
        this.title = title;
        this.content = content;
        this.thumbnail = thumbnail;
        this.postType = postType;
    }
}
