package com.develop_ping.union.user.presentation.dto.response;

import com.develop_ping.union.user.domain.dto.UserInfo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OtherUserResponse {
    private String token;
    private String nickname;
    private String description;
    private String univName;
    private String profileImage;
    private boolean isBlocked;

    @Builder
    private OtherUserResponse(String token, String nickname, String description, String univName, String profileImage, boolean isBlocked) {
        this.token = token;
        this.nickname = nickname;
        this.description = description;
        this.univName = univName;
        this.profileImage = profileImage;
        this.isBlocked = isBlocked;
    }

    public static OtherUserResponse from (UserInfo userInfo) {
        return OtherUserResponse.builder()
                .token(userInfo.getToken())
                .nickname(userInfo.getNickname())
                .description(userInfo.getDescription())
                .univName(userInfo.getUnivName())
                .profileImage(userInfo.getProfileImage())
                .isBlocked(userInfo.isBlocked())
                .build();
    }
}
