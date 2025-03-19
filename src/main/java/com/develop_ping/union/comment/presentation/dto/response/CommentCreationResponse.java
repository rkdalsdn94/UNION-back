package com.develop_ping.union.comment.presentation.dto.response;

import com.develop_ping.union.comment.domain.dto.CommentInfo;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentCreationResponse {
    private Long id;

    @Builder
    public CommentCreationResponse(Long id) {
        this.id = id;
    }

    public static CommentCreationResponse from(CommentInfo info) {
        return CommentCreationResponse.builder()
                .id(info.getId())
                .build();
    }
}
