package com.develop_ping.union.gathering.domain.service;

import com.develop_ping.union.IntegrationTestSupport;
import com.develop_ping.union.gathering.domain.GatheringManager;
import com.develop_ping.union.gathering.domain.dto.request.GatheringCommand;
import com.develop_ping.union.gathering.domain.dto.response.GatheringInfo;
import com.develop_ping.union.gathering.domain.entity.Gathering;
import com.develop_ping.union.party.domain.entity.Party;
import com.develop_ping.union.party.domain.entity.PartyRole;
import com.develop_ping.union.party.infra.PartyRepository;
import com.develop_ping.union.user.domain.entity.User;
import com.develop_ping.union.user.infra.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.ZonedDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("GatheringServiceImpl 통합 테스트")
class GatheringServiceImplIntegrationTest extends IntegrationTestSupport {

    @Autowired
    private GatheringService gatheringService;

    @Autowired
    private GatheringManager gatheringManager;

    @Autowired
    private PartyRepository partyRepository;

    @Autowired
    private UserRepository userRepository;

    @DisplayName("Gathering과 Party가 정상적으로 생성된다")
    @Test
    void givenGatheringCommandAndUser_whenCreateGathering_thenGatheringAndPartyAreSaved() {
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
        User user = createUser(); // 테스트용 User 객체 생성 및 저장

        // when
        GatheringInfo gatheringInfo = gatheringService.createGathering(command, user);
        Gathering gathering = gatheringManager.findById(gatheringInfo.getId()); // 생성된 Gathering을 조회

        // then
        assertThat(gatheringInfo).isNotNull();
        assertThat(gatheringInfo.getId()).isNotNull();
        assertThat(gatheringInfo.getTitle()).isEqualTo("모임 제목");

        Party savedParty = partyRepository.findByGatheringAndUser(gathering, user)
                                          .orElse(null);
        assertThat(savedParty).isNotNull();
        assertThat(savedParty.getGathering().getId()).isEqualTo(gatheringInfo.getId());
        assertThat(savedParty.getUser().getId()).isEqualTo(user.getId());
        assertThat(savedParty.getRole()).isEqualTo(PartyRole.OWNER);
    }

    // 테스트용 유저 생성 및 저장 메서드
    private User createUser() {
        User user = User.builder()
                        .nickname("테스트 유저")
                        .email("testuser@example.com")
                        .build();
        return userRepository.save(user); // DB에 저장 후 반환
    }
}
