package com.develop_ping.union.user.presentation.dto.request;

import com.develop_ping.union.user.domain.dto.UserCommand;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UpdateRequest {
    @NotNull
    @Size(max = 30)
    private String nickname;
    @NotNull
    @Size(max = 50)
    private String profileImage;
    @Size(max = 50)
    private String description;

    public UserCommand toCommand(Long userId) {
        return UserCommand.builder()
                .nickname(nickname)
                .profileImage(profileImage)
                .description(description)
                .userId(userId)
                .build();
    }
}
