package com.develop_ping.union.user.domain.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SignUpCommand {
    private String OauthUserToken;
    private String nickname;
    private String description;
    private String profileImage;
    private String univName;

    @Builder
    public SignUpCommand(String oauthUserToken, String nickname, String description, String profileImage, String univName) {
        OauthUserToken = oauthUserToken;
        this.nickname = nickname;
        this.description = description;
        this.profileImage = profileImage;
        this.univName = univName;
    }
}
