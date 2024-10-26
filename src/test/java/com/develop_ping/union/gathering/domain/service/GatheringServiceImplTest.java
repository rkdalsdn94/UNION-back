package com.develop_ping.union.gathering.domain.service;

import com.develop_ping.union.IntegrationTestSupport;
import com.develop_ping.union.gathering.domain.dto.GatheringCommand;
import com.develop_ping.union.gathering.domain.dto.GatheringInfo;
import com.develop_ping.union.party.domain.entity.Party;
import com.develop_ping.union.party.domain.entity.PartyRole;
import com.develop_ping.union.party.infra.PartyRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.ZonedDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("GatheringServiceImpl 통합 테스트")
class GatheringServiceImplIntegrationTest extends IntegrationTestSupport {

    @Autowired
    private GatheringServiceImpl gatheringService;

    @Autowired
    private PartyRepository partyRepository;

    @DisplayName("Gathering과 Party가 정상적으로 생성된다")
    @Test
    void givenGatheringCommandAndUserId_whenCreateGathering_thenGatheringAndPartyAreSaved() {
        // given
        GatheringCommand command = GatheringCommand.builder()
                                                   .title("모임 제목")
                                                   .content("모임 내용")
                                                   .maxMember(5)
                                                   .gatheringDateTime(ZonedDateTime.now().plusHours(2))
                                                    .address("장소")
                                                    .latitude(37.123456)
                                                    .longitude(127.123456)
                                                    .build();
        Long userId = 1L;

        // when
        GatheringInfo gatheringInfo = gatheringService.createGathering(command, userId);

        // then
        assertThat(gatheringInfo).isNotNull();
        assertThat(gatheringInfo.getId()).isNotNull();
        assertThat(gatheringInfo.getTitle()).isEqualTo("모임 제목");

        Party savedParty = partyRepository.findByGatheringIdAndUserId(gatheringInfo.getId(), userId)
                                          .orElse(null);
        assertThat(savedParty).isNotNull();
        assertThat(savedParty.getGatheringId()).isEqualTo(gatheringInfo.getId());
        assertThat(savedParty.getUserId()).isEqualTo(userId);
        assertThat(savedParty.getRole()).isEqualTo(PartyRole.OWNER);
    }
}
