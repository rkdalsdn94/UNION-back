package com.develop_ping.union.comment.presentation.dto.response;

import com.develop_ping.union.comment.domain.dto.CommentInfo;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentUpdateResponse {
    private Long id;

    @Builder
    public CommentUpdateResponse(Long id) {
        this.id = id;
    }

    public static CommentUpdateResponse from(CommentInfo info) {
        return CommentUpdateResponse.builder()
                .id(info.getId())
                .build();
    }
}
