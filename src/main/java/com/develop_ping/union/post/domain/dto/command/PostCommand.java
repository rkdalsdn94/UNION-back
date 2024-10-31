package com.develop_ping.union.post.domain.dto.command;

import com.develop_ping.union.post.domain.entity.PostType;
import com.develop_ping.union.user.domain.entity.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostCommand {
    private User user;
    private Long id;
    private String title;
    private String content;
    private String thumbnail;
    private PostType postType;

    @Builder
    public PostCommand(User user,
                       Long id,
                       String title,
                       String content,
                       String thumbnail,
                       PostType postType) {
        this.user = user;
        this.id = id;
        this.title = title;
        this.content = content;
        this.thumbnail = thumbnail;
        this.postType = postType;
    }

    public static PostCommand of(User user, Long id) {
        return PostCommand.builder()
                .user(user)
                .id(id)
                .build();
    }
}
