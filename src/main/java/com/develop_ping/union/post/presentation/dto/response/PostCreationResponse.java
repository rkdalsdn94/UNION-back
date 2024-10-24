package com.develop_ping.union.post.presentation.dto.response;

import com.develop_ping.union.post.domain.dto.info.PostCreationInfo;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class PostCreationResponse {
    private Long id;

    @Builder
    public PostCreationResponse(Long id) {
        this.id = id;
    }

    public static PostCreationResponse from(PostCreationInfo postCreationInfo) {
        return PostCreationResponse.builder()
            .id(postCreationInfo.getId())
            .build();
    }
}
