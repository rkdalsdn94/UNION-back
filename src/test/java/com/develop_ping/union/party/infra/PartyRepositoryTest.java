package com.develop_ping.union.party.infra;

import com.develop_ping.union.IntegrationTestSupport;
import com.develop_ping.union.gathering.domain.entity.Gathering;
import com.develop_ping.union.gathering.infra.GatheringRepository;
import com.develop_ping.union.party.domain.entity.Party;
import com.develop_ping.union.party.domain.entity.PartyRole;
import com.develop_ping.union.user.domain.entity.User;
import com.develop_ping.union.user.infra.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("PartyRepository 테스트")
@Transactional
class PartyRepositoryTest extends IntegrationTestSupport {

    @Autowired
    private PartyRepository partyRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GatheringRepository gatheringRepository;

    @DisplayName("Party 엔티티를 저장하고 조회할 수 있다")
    @Test
    void Party_엔티티를_저장하고_조회할_수_있다() {
        // given
        User user = createUser();
        Gathering gathering = createGathering();

        PartyRole role = PartyRole.OWNER;
        Party party = Party.builder()
                           .gathering(gathering)
                           .user(user)
                           .role(role)
                           .build();

        // when
        Party savedParty = partyRepository.save(party);

        // then
        assertThat(savedParty).isNotNull();
        assertThat(savedParty.getGathering().getId()).isEqualTo(gathering.getId());
        assertThat(savedParty.getUser().getId()).isEqualTo(user.getId());
        assertThat(savedParty.getRole()).isEqualTo(role);
    }

    @DisplayName("Gathering과 User로 Party를 조회할 수 있다")
    @Test
    void Gathering과_User로_Party를_조회할_수_있다() {
        // given
        User user = createUser();
        Gathering gathering = createGathering();

        Party party = Party.builder()
                           .gathering(gathering)
                           .user(user)
                           .role(PartyRole.OWNER)
                           .build();
        partyRepository.save(party);

        // when
        Party foundParty = partyRepository.findByGatheringAndUser(gathering, user)
                                          .orElse(null);

        // then
        assertThat(foundParty).isNotNull();
        assertThat(foundParty.getGathering().getId()).isEqualTo(gathering.getId());
        assertThat(foundParty.getUser().getId()).isEqualTo(user.getId());
    }

    // 테스트용 User 생성 및 저장
    private User createUser() {
        User user = User.builder()
                        .nickname("테스트 유저")
                        .email("testuser@example.com")
                        .build();
        return userRepository.save(user);
    }

    // 테스트용 Gathering 생성 및 저장
    private Gathering createGathering() {
        Gathering gathering = Gathering.builder()
                                       .title("모임 제목")
                                       .content("모임 내용")
                                       .maxMember(5)
                                       .gatheringDateTime(ZonedDateTime.now().plusHours(2))
                                       .build();
        return gatheringRepository.save(gathering);
    }
}
