package com.develop_ping.union.user.presentation.dto.request;

import com.develop_ping.union.user.domain.dto.SignUpCommand;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RegisterRequest {
    @NotNull
    private String oauthUserToken;
    @NotNull
    private String nickname;
    private String description;
    @NotNull
    private String profileImage;
    @NotNull
    private String univName;

    public SignUpCommand toCommand () {
        return SignUpCommand.builder()
                .oauthUserToken(oauthUserToken)
                .description(description)
                .profileImage(profileImage)
                .univName(univName)
                .nickname(nickname)
                .build();
    }
}
