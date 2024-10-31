package com.develop_ping.union.post.presentation.dto.request;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostListRequest {
    private Integer page;
    private Integer size;

    @Builder
    public PostListRequest(Integer page, Integer size) {
        this.page = page;
        this.size = size;
    }
}
