package com.develop_ping.union.post.presentation.dto.response;

import com.develop_ping.union.post.domain.dto.info.PostInfo;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class PostCreationResponse {
    private Long id;

    @Builder
    public PostCreationResponse(Long id) {
        this.id = id;
    }

    public static PostCreationResponse from(PostInfo postInfo) {
        return PostCreationResponse.builder()
            .id(postInfo.getId())
            .build();
    }
}
