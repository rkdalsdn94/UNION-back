package com.develop_ping.union.post.presentation.dto.response;

import com.develop_ping.union.post.domain.dto.info.PostInfo;
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

    public static PostUpdateResponse from(PostInfo postUpdateInfo) {
        return PostUpdateResponse.builder()
                .id(postUpdateInfo.getId())
                .build();
    }
}
