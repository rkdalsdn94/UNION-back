package com.develop_ping.union.party.presentation.response;

import com.develop_ping.union.party.domain.entity.Party;
import com.develop_ping.union.user.domain.entity.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class PartyListResponse {

    private final String name;

    @Builder
    private PartyListResponse(String name) {
        this.name = name;
    }

    public static PartyListResponse from(Party party) {
        return PartyListResponse.builder()
                                .name(party.getUser().getName())
                                .build();
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class AuthorResponse {
        private String name;

        @Builder
        private AuthorResponse(String name) {
            this.name = name;
        }

        public static AuthorResponse from(User user) {
            return AuthorResponse
                .builder()
                .name(user.getName())
                .build();
        }
    }
}
