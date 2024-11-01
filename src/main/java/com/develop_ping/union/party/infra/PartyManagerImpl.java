package com.develop_ping.union.party.infra;

import com.develop_ping.union.gathering.domain.GatheringManager;
import com.develop_ping.union.gathering.domain.entity.Gathering;
import com.develop_ping.union.gathering.exception.GatheringNotFoundException;
import com.develop_ping.union.gathering.exception.ParticipantLimitExceededException;
import com.develop_ping.union.party.domain.PartyManager;
import com.develop_ping.union.party.domain.dto.PartyInfo;
import com.develop_ping.union.party.domain.entity.Party;
import com.develop_ping.union.party.domain.entity.PartyRole;
import com.develop_ping.union.party.exception.AlreadyJoinedException;
import com.develop_ping.union.user.domain.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PartyManagerImpl implements PartyManager {

    private final PartyRepository partyRepository;
    private final GatheringManager gatheringManager;

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
    public Party findByGatheringAndUser(Gathering gathering, User user) {
        return partyRepository.findByGatheringAndUser(gathering, user)
                              .orElseThrow(() -> new GatheringNotFoundException(gathering.getId()));
    }

    @Override
    public void deleteParty(Gathering gathering) {
        partyRepository.deleteByGathering(gathering);
    }

    @Override
    public void joinGathering(Gathering gathering, User user) {
        gathering.incrementCurrentMember();
        validateJoinConditions(gathering, user);

        Party party = Party.builder()
                           .gathering(gathering)
                           .user(user)
                           .role(PartyRole.PARTICIPANT)
                           .build();

        partyRepository.save(party);
        gatheringManager.save(gathering);
    }

    @Override
    public String findOwnerNicknameByGathering(Gathering gathering) {
        return partyRepository.findOwnerNicknameByGatheringAndRole(gathering, PartyRole.OWNER);
    }

    @Override
    public boolean existsByGatheringAndUserAndRole(Gathering gathering, User user, PartyRole role) {
        return partyRepository.existsByGatheringAndUserAndRole(gathering, user, role);
    }

    @Override
    public boolean existsByGatheringAndUser(Gathering gathering, User user) {
        return partyRepository.existsByGatheringAndUser(gathering, user);
    }

    @Override
    public void deleteByGatheringAndUser(Gathering gathering, User user) {
        partyRepository.deleteByGatheringAndUser(gathering, user);
    }

    @Override
    public void delete(Party party) {
        partyRepository.delete(party);
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
