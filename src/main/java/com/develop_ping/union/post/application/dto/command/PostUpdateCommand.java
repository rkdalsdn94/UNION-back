package com.develop_ping.union.post.application.dto.command;

import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostUpdateCommand {
    private String token;
    private Long id;
    private String title;
    private String content;

    @Builder
    public PostUpdateCommand(String token, Long id, String title, String content) {
        this.token = token;
        this.id = id;
        this.title = title;
        this.content = content;
    }
}
