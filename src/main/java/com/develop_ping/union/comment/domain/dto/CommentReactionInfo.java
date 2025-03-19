package com.develop_ping.union.comment.domain.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class CommentReactionInfo {
    private final long commentLikes;
    private final boolean liked;

    @Builder
    private CommentReactionInfo(long commentLikes, boolean liked) {
        this.commentLikes = commentLikes;
        this.liked = liked;
    }

    public static CommentReactionInfo of(long commentLikes, boolean liked) {
        return CommentReactionInfo.builder()
                .commentLikes(commentLikes)
                .liked(liked)
                .build();
    }
}
