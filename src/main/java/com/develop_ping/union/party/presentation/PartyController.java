package com.develop_ping.union.party.presentation;

import com.develop_ping.union.party.domain.entity.Party;
import com.develop_ping.union.party.domain.service.PartyService;
import com.develop_ping.union.party.presentation.response.PartyListResponse;
import com.develop_ping.union.user.domain.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
public class PartyController {

    private final PartyService partyService;

    @GetMapping("/parties/{gatheringId}")
    public List<PartyListResponse> getPartyList(
        @AuthenticationPrincipal User user,
        @PathVariable("gatheringId") Long gatheringId
    ) {
        log.info("파티 리스트 조회 요청 - userId: {}", user.getId());

        List<Party> parties = partyService.findByGatheringId(gatheringId);

        return parties.stream()
                      .map(PartyListResponse::from)
                      .collect(Collectors.toList());
    }
}
