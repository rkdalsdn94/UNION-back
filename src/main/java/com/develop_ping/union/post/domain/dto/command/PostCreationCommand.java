package com.develop_ping.union.post.domain.dto.command;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostCreationCommand {
    private String token;
    private String title;
    private String content;
    private String thumbnail;
    private String type;

    @Builder
    public PostCreationCommand(String token, String title, String content, String thumbnail,String type) {
        this.token = token;
        this.title = title;
        this.content = content;
        this.thumbnail = thumbnail;
        this.type = type;
    }
}
