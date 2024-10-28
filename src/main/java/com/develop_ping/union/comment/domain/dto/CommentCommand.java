package com.develop_ping.union.comment.domain.dto;

import com.develop_ping.union.user.domain.entity.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentCommand {
    private Long id;
    private String content;
    private Long postId;
    private User user;
    private Long parentId;

    @Builder
    public CommentCommand(Long id, String content, Long postId, User user, Long parentId) {
        this.id = id;
        this.content = content;
        this.postId = postId;
        this.user = user;
        this.parentId = parentId;
    }

    public static CommentCommand deletionOf(Long id, User user) {
        return CommentCommand.builder()
                .id(id)
                .user(user)
                .build();
    }
}
