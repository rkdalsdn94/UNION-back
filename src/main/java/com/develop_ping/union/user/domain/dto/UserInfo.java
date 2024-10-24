package com.develop_ping.union.user.domain.dto;

import com.develop_ping.union.user.domain.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserInfo {
    private String accessToken;
    private String refreshToken;
    private String token;
    private String nickname;
    private String description;
    private String univName;
    private String profileImage;

    @Builder
    private UserInfo(String accessToken, String refreshToken, String token, String nickname, String description, String univName, String profileImage) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.token = token;
        this.nickname = nickname;
        this.description = description;
        this.univName = univName;
        this.profileImage = profileImage;
    }

    public static UserInfo of (User user,String accessToken, String refreshToken) {
        return UserInfo.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .token(user.getToken())
                .nickname(user.getNickname())
                .univName(user.getUnivName())
                .profileImage(user.getProfileImage())
                .description(user.getDescription())
                .build();
    }
}
