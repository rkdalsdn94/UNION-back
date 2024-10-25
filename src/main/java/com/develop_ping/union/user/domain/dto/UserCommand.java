package com.develop_ping.union.user.domain.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserCommand {
    private String OauthUserToken;
    private String userToken;
    private Long userId;
    private String nickname;
    private String description;
    private String profileImage;
    private String univName;

    @Builder
    public UserCommand(String oauthUserToken, String nickname, String description, String profileImage, String univName, String userToken, Long userId) {
        OauthUserToken = oauthUserToken;
        this.nickname = nickname;
        this.description = description;
        this.profileImage = profileImage;
        this.univName = univName;
        this.userToken = userToken;
        this.userId = userId;
    }
}
