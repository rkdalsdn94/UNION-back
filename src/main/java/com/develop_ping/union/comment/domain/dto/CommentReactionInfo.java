package com.develop_ping.union.comment.domain.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class CommentReactionInfo {
    private final long commentLikes;
    private final boolean isLiked;

    @Builder
    private CommentReactionInfo(long commentLikes, boolean isLiked) {
        this.commentLikes = commentLikes;
        this.isLiked = isLiked;
    }

    public static CommentReactionInfo of(long commentLikes, boolean isLiked) {
        return CommentReactionInfo.builder()
                .commentLikes(commentLikes)
                .isLiked(isLiked)
                .build();
    }
}
