package com.develop_ping.union.party.infra;

import com.develop_ping.union.party.domain.PartyManager;
import com.develop_ping.union.party.domain.dto.PartyInfo;
import com.develop_ping.union.party.domain.entity.Party;
import com.develop_ping.union.party.domain.entity.PartyRole;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PartyManagerImpl implements PartyManager {

    private final PartyRepository partyRepository;

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
    public PartyInfo findByGatheringId(Long gatheringId) {
        Party party = partyRepository.findByGatheringId(gatheringId)
                                     .orElseThrow(() -> new IllegalArgumentException("해당 모임이 존재하지 않습니다."));

        return PartyInfo.of(party);
    }

    // 파티 참여자 정보 구하는 API
    @Override
    public Long findOwnerByGatheringId(Long gatheringId) {
        Party party = partyRepository.findByGatheringIdAndRole(gatheringId, PartyRole.OWNER)
                                     .orElseThrow(() -> new IllegalArgumentException("해당 모임의 오너가 존재하지 않습니다."));
        return party.getUserId();
    }
}
