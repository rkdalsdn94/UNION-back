package com.develop_ping.union.post.application.dto.command;

import lombok.*;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostCreationCommand {
    private String token;
    private String title;
    private String content;
    private List<String> photos;
    private String type;

    @Builder
    public PostCreationCommand(String token, String title, String content, List<String> photos, String type) {
        this.token = token;
        this.title = title;
        this.content = content;
        this.photos = photos;
        this.type = type;
    }
}
