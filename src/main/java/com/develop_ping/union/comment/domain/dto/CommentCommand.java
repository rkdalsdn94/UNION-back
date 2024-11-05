package com.develop_ping.union.comment.domain.dto;

import com.develop_ping.union.user.domain.entity.User;
import lombok.Builder;
import lombok.Getter;

@Getter
public class CommentCommand {
    private final Long id;
    private final String content;
    private final Long postId;
    private final User user;
    private final Long parentId;
    private final String parentNickname;

    @Builder
    public CommentCommand(Long id,
                          String content,
                          Long postId,
                          User user,
                          Long parentId,
                          String parentNickname) {
        this.id = id;
        this.content = content;
        this.postId = postId;
        this.user = user;
        this.parentId = parentId;
        this.parentNickname = parentNickname;
    }

    public static CommentCommand deletionOf(Long id, User user) {
        return CommentCommand.builder()
                .id(id)
                .user(user)
                .build();
    }

    public static CommentCommand getOf(Long postId, User user) {
        return CommentCommand.builder()
                .postId(postId)
                .user(user)
                .build();
    }
}
