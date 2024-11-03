package com.develop_ping.union.party.infra;

import com.develop_ping.union.gathering.domain.entity.Gathering;
import com.develop_ping.union.gathering.exception.GatheringNotFoundException;
import com.develop_ping.union.gathering.exception.ParticipantLimitExceededException;
import com.develop_ping.union.party.domain.PartyManager;
import com.develop_ping.union.party.domain.dto.PartyInfo;
import com.develop_ping.union.party.domain.entity.Party;
import com.develop_ping.union.party.domain.entity.PartyRole;
import com.develop_ping.union.party.exception.AlreadyJoinedException;
import com.develop_ping.union.party.exception.ParticipationNotFoundException;
import com.develop_ping.union.user.domain.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class PartyManagerImpl implements PartyManager {
 private final PartyRepository partyRepository;

    @Override
    public PartyInfo createParty(Gathering gathering, User user) {
        Party savedParty = partyRepository.save(
            Party.builder()
                 .user(user)
                 .gathering(gathering)
                 .role(PartyRole.OWNER)
                 .build()
        );
        return PartyInfo.of(savedParty);
    }

    @Override
    public boolean existsByGatheringAndUser(Gathering gathering, User user) {
        return partyRepository.existsByGatheringAndUser(gathering, user);
    }

    @Override
    public void exitGathering(Gathering gathering, User user) {
        log.info("\n모임 참여자 삭제 - 나가기 기능 PartyManagerImpl 클래스 : gathering: {}, user: {}", gathering.getId(), user.getId());

        partyRepository.findByGatheringAndUser(gathering, user)
                                    .orElseThrow(() -> new ParticipationNotFoundException("참여 정보가 없습니다."));

        partyRepository.deleteByGatheringAndUser(gathering, user);
    }

    @Override
    public Party findOwnerByGathering(Long gatheringId) {
        return partyRepository.findByGatheringId(gatheringId)
                              .orElseThrow(() -> new GatheringNotFoundException(gatheringId));
    }

    @Override
    public Optional<Party> findByGatheringAndUser(Gathering gathering, User user) {
        return partyRepository.findByGatheringAndUser(gathering, user);
    }

    @Override
    public void save(Party party) {
        partyRepository.save(party);
    }

    // TODO: 도메인 로직으로 이동 고려
    private void validateJoinConditions(Gathering gathering, User user) {
        if (partyRepository.existsByGatheringAndUser(gathering, user)) {
            throw new AlreadyJoinedException("이미 해당 모임에 참여하셨습니다.");
        }

        if (gathering.getCurrentMember() > gathering.getMaxMember()) {
            throw new ParticipantLimitExceededException("모임 인원이 가득 찼습니다.");
        }
    }
}
