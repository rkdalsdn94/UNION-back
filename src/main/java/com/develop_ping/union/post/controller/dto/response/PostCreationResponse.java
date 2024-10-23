package com.develop_ping.union.post.controller.dto.response;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class PostCreationResponse {
    private Long id;

    @Builder
    public PostCreationResponse(Long id) {
        this.id = id;
    }
}
