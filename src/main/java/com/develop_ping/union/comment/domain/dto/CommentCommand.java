package com.develop_ping.union.comment.domain.dto;

import com.develop_ping.union.user.domain.entity.User;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentCommand {
    private Long id;
    private String content;
    private Long postId;
    private User user;
    private Long parentId;
    private String parentNickname;

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
