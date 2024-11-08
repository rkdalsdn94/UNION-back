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
    private boolean liked;

    @Builder
    private CommentReactionResponse(long commentLikes, boolean liked) {
        this.commentLikes = commentLikes;
        this.liked = liked;
    }

    public static CommentReactionResponse from(CommentReactionInfo info) {
        return CommentReactionResponse.builder()
                .commentLikes(info.getCommentLikes())
                .liked(info.isLiked())
                .build();
    }
}
