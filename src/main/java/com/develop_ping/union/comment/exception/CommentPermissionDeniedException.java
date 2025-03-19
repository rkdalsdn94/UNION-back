package com.develop_ping.union.comment.exception;

import lombok.Getter;

@Getter
public class CommentPermissionDeniedException extends RuntimeException{
    private final Long userId;
    private final Long commentId;

    public CommentPermissionDeniedException(Long userId, Long commentId) {
        this.userId = userId;
        this.commentId = commentId;
    }
}
