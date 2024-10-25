package com.develop_ping.union.party.infra;

import com.develop_ping.union.IntegrationTestSupport;
import com.develop_ping.union.party.domain.entity.Party;
import com.develop_ping.union.party.domain.entity.PartyRole;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("PartyRepository 테스트")
@Transactional
class PartyRepositoryTest extends IntegrationTestSupport {

    @Autowired
    private PartyRepository partyRepository;

    @DisplayName("Party 엔티티를 저장하고 조회할 수 있다")
    @Test
    void Party_엔티티를_저장하고_조회할_수_있다() {
        // given
        Long gatheringId = 1L;
        Long userId = 1L;
        PartyRole role = PartyRole.OWNER;

        Party party = Party.builder()
                           .gatheringId(gatheringId)
                           .userId(userId)
                           .role(role)
                           .build();

        // when
        Party savedParty = partyRepository.save(party);

        // then
        assertThat(savedParty).isNotNull();
        assertThat(savedParty.getGatheringId()).isEqualTo(gatheringId);
        assertThat(savedParty.getUserId()).isEqualTo(userId);
        assertThat(savedParty.getRole()).isEqualTo(role);
    }

    @DisplayName("GatheringId와 UserId로 Party를 조회할 수 있다")
    @Test
    void GatheringId와_UserId로_Party를_조회할_수_있다() {
        // given
        Long gatheringId = 1L;
        Long userId = 1L;
        Party party = Party.builder()
                           .gatheringId(gatheringId)
                           .userId(userId)
                           .role(PartyRole.OWNER)
                           .build();
        partyRepository.save(party);

        // when
        Party foundParty = partyRepository.findByGatheringIdAndUserId(gatheringId, userId)
                                          .orElse(null);

        // then
        assertThat(foundParty).isNotNull();
        assertThat(foundParty.getGatheringId()).isEqualTo(gatheringId);
        assertThat(foundParty.getUserId()).isEqualTo(userId);
    }
}
