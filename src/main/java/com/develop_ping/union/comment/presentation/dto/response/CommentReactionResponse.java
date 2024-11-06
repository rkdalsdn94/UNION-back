package com.develop_ping.union.comment.presentation.dto.response;

import com.develop_ping.union.comment.domain.dto.CommentReactionInfo;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentReactionResponse {
    private long commentLikes;
    private boolean isLiked;

    @Builder
    private CommentReactionResponse(long commentLikes, boolean isLiked) {
        this.commentLikes = commentLikes;
        this.isLiked = isLiked;
    }

    public static CommentReactionResponse from(CommentReactionInfo info) {
        return CommentReactionResponse.builder()
                .commentLikes(info.getCommentLikes())
                .isLiked(info.isLiked())
                .build();
    }
}
