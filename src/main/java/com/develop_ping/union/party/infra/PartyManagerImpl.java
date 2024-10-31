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
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PartyManagerImpl implements PartyManager {

    private final PartyRepository partyRepository;
    private final GatheringManager gatheringManager;

    @Override
    public PartyInfo createParty(Long gatheringId, Long userId) {

        Party savedParty = partyRepository.save(Party.builder()
                                                     .userId(userId)
                                                     .gatheringId(gatheringId)
                                                     .role(PartyRole.OWNER)
                                                     .build());
        return PartyInfo.of(savedParty);
    }

    @Override
    public Party findByGatheringId(Long gatheringId) {
        return partyRepository.findByGatheringId(gatheringId)
                              .orElseThrow(() -> new GatheringNotFoundException(gatheringId));
    }

    @Override
    public void deleteParty(Long gatheringId) {
        partyRepository.deleteByGatheringId(gatheringId);
    }

    @Override
    public void joinGathering(Long gatheringId, Long userId) {
        Gathering gathering = gatheringManager.findWithPessimisticLockById(gatheringId)
                                              .orElseThrow(() -> new GatheringNotFoundException(gatheringId));

        gathering.incrementCurrentMember();
        validateJoinConditions(gatheringId, userId, gathering);

        Party party = Party.builder()
                           .gatheringId(gatheringId)
                           .userId(userId)
                           .role(PartyRole.PARTICIPANT)
                           .build();

        partyRepository.save(party);
        gatheringManager.save(gathering);
    }

    @Override
    public boolean existsByGatheringIdAndUserId(Long gatheringId, Long userId) {
        return partyRepository.existsByGatheringIdAndUserId(gatheringId, userId);
    }

    // TODO: 도메인 로직으로 내리는 것이 좋을까?
    private void validateJoinConditions(Long gatheringId, Long userId, Gathering gathering) {
        if (partyRepository.existsByGatheringIdAndUserId(gatheringId, userId)) {
            throw new AlreadyJoinedException("이미 해당 모임에 참여하셨습니다.");
        }

        if (gathering.getCurrentMember() > gathering.getMaxMember()) {
            throw new ParticipantLimitExceededException("모임 인원이 가득 찼습니다.");
        }
    }
}
