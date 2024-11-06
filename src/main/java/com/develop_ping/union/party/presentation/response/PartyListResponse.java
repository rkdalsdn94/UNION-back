package com.develop_ping.union.party.presentation.response;

import com.develop_ping.union.party.domain.entity.Party;
import com.develop_ping.union.user.domain.entity.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class PartyListResponse {

    private final String email;
    private final String token;
    private final String nickname;
    private final String description;
    private final String profileImage;
    private final String univName;
    private final Long gatheringId;
    private final String partyRole;

    @Builder
    private PartyListResponse(AuthorResponse user, Long gatheringId, String partyRole) {
        this.email = user.email;
        this.token = user.token;
        this.nickname = user.nickname;
        this.description = user.description;
        this.profileImage = user.profileImage;
        this.univName = user.univName;
        this.gatheringId = gatheringId;
        this.partyRole = partyRole;
    }

    public static PartyListResponse from(Party party) {
        return PartyListResponse.builder()
                                .user(AuthorResponse.from(party.getUser()))
                                .gatheringId(party.getGathering().getId())
                                .partyRole(party.getRole().name())
                                .build();
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class AuthorResponse {
        private String email;
        private String token;
        private String nickname;
        private String profileImage;
        private String univName;
        private String description;

        @Builder
        private AuthorResponse(
            String email, String token, String nickname,
            String profileImage, String univName, String description
        ) {
            this.email = email;
            this.token = token;
            this.nickname = nickname;
            this.profileImage = profileImage;
            this.univName = univName;
            this.description = description;
        }

        public static AuthorResponse from(User user) {
            return AuthorResponse.builder()
                                 .email(user.getEmail())
                                 .token(user.getToken())
                                 .nickname(user.getNickname())
                                 .description(user.getDescription())
                                 .profileImage(user.getProfileImage())
                                 .univName(user.getUnivName())
                                 .build();
        }
    }
}
