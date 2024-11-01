package com.develop_ping.union.reaction.presentation.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostReactionResponse {
    private int PostLikes;
    private boolean liked;

    @Builder
    public PostReactionResponse(int postLikes, boolean liked) {
        this.PostLikes = postLikes;
        this.liked = liked;
    }
}
