package com.develop_ping.union.party.domain;

import com.develop_ping.union.party.domain.dto.PartyInfo;
import com.develop_ping.union.party.domain.entity.Party;

public interface PartyManager {

    PartyInfo createParty(Long gatheringId, Long userId);
    Party findByGatheringId(Long gatheringId);
    void deleteParty(Long gatheringId);
    void joinGathering(Long gatheringId, Long userId);
}
