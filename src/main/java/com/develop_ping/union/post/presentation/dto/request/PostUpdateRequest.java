package com.develop_ping.union.post.presentation.dto.request;

import com.develop_ping.union.post.domain.dto.command.PostUpdateCommand;
import com.develop_ping.union.user.domain.entity.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    public PostUpdateCommand toCommand(User user, Long id) {
        return PostUpdateCommand.builder()
                .user(user)
                .id(id)
                .title(title)
                .content(content)
                .build();
    }
}
