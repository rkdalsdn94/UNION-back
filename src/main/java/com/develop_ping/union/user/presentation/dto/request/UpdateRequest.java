package com.develop_ping.union.user.presentation.dto.request;

import com.develop_ping.union.user.domain.dto.UserCommand;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UpdateRequest {
    @Size(max = 30)
    private String nickname;
    @Size(max = 255)
    private String profileImage;
    @Size(max = 50)
    private String description;

    public UserCommand toCommand() {
        return UserCommand.builder()
                .nickname(nickname)
                .profileImage(profileImage)
                .description(description)
                .build();
    }
}
