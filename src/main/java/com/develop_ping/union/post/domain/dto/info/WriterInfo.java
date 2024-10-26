package com.develop_ping.union.post.domain.dto.info;

import com.develop_ping.union.user.domain.entity.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class WriterInfo {
    private String nickname;
    private String profileImage;
    private String univName;

    @Builder
    public WriterInfo(String nickname,
                      String profileImage,
                      String univName) {
        this.nickname = nickname;
        this.profileImage = profileImage;
        this.univName = univName;
    }

    public static WriterInfo of(User user) {
        return WriterInfo.builder()
                .nickname(user.getNickname())
                .profileImage(user.getProfileImage())
                .univName(user.getUnivName())
                .build();
    }
}
