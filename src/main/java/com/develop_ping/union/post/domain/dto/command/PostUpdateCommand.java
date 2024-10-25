package com.develop_ping.union.post.domain.dto.command;

import com.develop_ping.union.user.domain.entity.User;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostUpdateCommand {
    private User user;
    private Long id;
    private String title;
    private String content;

    @Builder
    public PostUpdateCommand(User user, Long id, String title, String content) {
        this.user = user;
        this.id = id;
        this.title = title;
        this.content = content;
    }
}
