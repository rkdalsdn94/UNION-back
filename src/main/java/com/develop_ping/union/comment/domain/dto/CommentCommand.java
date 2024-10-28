package com.develop_ping.union.comment.domain.dto;

import com.develop_ping.union.user.domain.entity.User;
import lombok.AccessLevel;
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
}
