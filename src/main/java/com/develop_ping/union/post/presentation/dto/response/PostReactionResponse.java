package com.develop_ping.union.post.presentation.dto.response;

import com.develop_ping.union.post.domain.dto.info.PostReactionInfo;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostReactionResponse {
    private long postLikes;
    private boolean isLiked;

    @Builder
    private PostReactionResponse(long postLikes, boolean isLiked) {
        this.postLikes = postLikes;
        this.isLiked = isLiked;
    }

    public static PostReactionResponse from(PostReactionInfo info) {
        return PostReactionResponse.builder()
                .postLikes(info.getPostLikes())
                .isLiked(info.isLiked())
                .build();
    }
}
