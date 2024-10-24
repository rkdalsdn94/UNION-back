package com.develop_ping.union.post.exception;

import lombok.Getter;

@Getter
public class PostPermissionDeniedException extends RuntimeException{
    private final Long userId;
    private final Long postId;

    public PostPermissionDeniedException(Long userId, Long postId) {
        this.userId = userId;
        this.postId = postId;
    }
}
