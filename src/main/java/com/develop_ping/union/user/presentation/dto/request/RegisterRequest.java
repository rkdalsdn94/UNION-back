package com.develop_ping.union.user.presentation.dto.request;

import com.develop_ping.union.user.domain.dto.SignUpCommand;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RegisterRequest {
    private String oauthUserToken;
    private String nickname;
    private String description;
    private String profileImage;
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
