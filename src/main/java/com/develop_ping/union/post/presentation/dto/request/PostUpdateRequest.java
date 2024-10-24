package com.develop_ping.union.post.presentation.dto.request;

import com.develop_ping.union.post.domain.dto.command.PostUpdateCommand;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostUpdateRequest {
    private String title;
    private String content;

    @Builder
    public PostUpdateRequest(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public PostUpdateCommand toCommand(String token, Long id) {
        return PostUpdateCommand.builder()
                .token(token)
                .id(id)
                .title(title)
                .content(content)
                .build();
    }
}
