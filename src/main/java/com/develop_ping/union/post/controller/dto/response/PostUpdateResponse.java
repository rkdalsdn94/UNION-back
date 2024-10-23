package com.develop_ping.union.post.controller.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class PostUpdateResponse {
    private Long id;

    @Builder
    public PostUpdateResponse(Long id) {
        this.id = id;
    }
}
