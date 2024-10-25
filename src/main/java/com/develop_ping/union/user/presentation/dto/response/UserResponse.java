package com.develop_ping.union.user.presentation.dto.response;

import com.develop_ping.union.user.domain.dto.UserInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserResponse {
    private String token;
    private String nickname;
    private String description;
    private String univName;
    private String profileImage;

    public UserResponse(UserInfo userInfo) {
        this.token = userInfo.getToken();
        this.nickname = userInfo.getNickname();
        this.description = userInfo.getDescription();
        this.univName = userInfo.getUnivName();
        this.profileImage = userInfo.getProfileImage();
    }
}
