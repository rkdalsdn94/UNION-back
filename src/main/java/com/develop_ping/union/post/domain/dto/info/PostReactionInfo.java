package com.develop_ping.union.post.domain.dto.info;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostReactionInfo {
    private long postLikes;
    private boolean isLiked;

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
