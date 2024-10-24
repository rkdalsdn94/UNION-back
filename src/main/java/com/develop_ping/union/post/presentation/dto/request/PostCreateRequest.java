package com.develop_ping.union.post.presentation.dto.request;

import com.develop_ping.union.post.domain.dto.command.PostCreationCommand;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostCreateRequest {
    @NotBlank
    @Size(min = 2, max = 50)
    private String title;

    @NotBlank
    @Size(min = 2, max = 2000)
    private String content;

    private List<String> photos;

    @Builder
    public PostCreateRequest(String title, String content, List<String> photos) {
        this.title = title;
        this.content = content;
        this.photos = photos;
    }

    public PostCreationCommand toCommand(String token, String type) {
        return PostCreationCommand.builder()
                .token(token)
                .title(this.title)
                .content(this.content)
                .photos(this.photos)
                .type(type)
                .build();
    }
}
