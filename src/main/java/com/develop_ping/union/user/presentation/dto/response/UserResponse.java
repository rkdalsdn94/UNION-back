package com.develop_ping.union.user.presentation.dto.response;

import com.develop_ping.union.user.domain.dto.UserInfo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserResponse {
    private String nickname;
    private String description;
    private String univName;
    private String profileImage;

    @Builder
    private UserResponse(String nickname, String description, String univName, String profileImage) {
        this.nickname = nickname;
        this.description = description;
        this.univName = univName;
        this.profileImage = profileImage;
    }

    public static UserResponse from (UserInfo userInfo) {
        return UserResponse.builder()
                .nickname(userInfo.getNickname())
                .description(userInfo.getDescription())
                .univName(userInfo.getUnivName())
                .profileImage(userInfo.getProfileImage())
                .build();
    }
}
