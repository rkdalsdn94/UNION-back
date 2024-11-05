package com.develop_ping.union.post.domain.dto.info;

import lombok.Builder;
import lombok.Getter;

@Getter
public class PostReactionInfo {
    private final long postLikes;
    private final boolean isLiked;

    @Builder
    private PostReactionInfo(long postLikes, boolean isLiked) {
        this.postLikes = postLikes;
        this.isLiked = isLiked;
    }

    public static PostReactionInfo of(long postLikes, boolean isLiked) {
        return PostReactionInfo.builder()
                .postLikes(postLikes)
                .isLiked(isLiked)
                .build();
    }
}
