package com.develop_ping.union.comment.presentation.dto.request;

import com.develop_ping.union.comment.domain.dto.CommentCommand;
import com.develop_ping.union.user.domain.entity.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentUpdateRequest {
    @NotBlank @Size(min = 1, max = 300)
    private String content;

    @NotNull
    private Long postId;

    public CommentCommand toCommand(Long id, User user) {
        return CommentCommand.builder()
                .id(id)
                .content(this.content)
                .postId(this.postId)
                .user(user)
                .build();
    }
}
