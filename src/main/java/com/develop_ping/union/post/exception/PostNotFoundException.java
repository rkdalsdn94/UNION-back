package com.develop_ping.union.post.exception;

import lombok.Getter;

@Getter
public class PostNotFoundException extends RuntimeException {
    private final Long postId;

    public PostNotFoundException(Long postId) {
        this.postId = postId;
    }
}
