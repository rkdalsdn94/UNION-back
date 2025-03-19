package com.develop_ping.union.comment.exception;

import lombok.Getter;

@Getter
public class CommentNotFoundException extends RuntimeException {
    private Long commentId;

    public CommentNotFoundException(Long commentId) {
        this.commentId = commentId;
    }
}
