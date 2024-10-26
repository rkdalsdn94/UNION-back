package com.develop_ping.union.post.domain.dto.command;

import com.develop_ping.union.user.domain.entity.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostDeleteCommand {
    private Long postId;
    private User user;

    @Builder
    public PostDeleteCommand(Long postId, User user) {
        this.postId = postId;
        this.user = user;
    }

    public static PostDeleteCommand of(Long postId, User user) {
        return PostDeleteCommand.builder()
                .postId(postId)
                .user(user)
                .build();
    }
}
