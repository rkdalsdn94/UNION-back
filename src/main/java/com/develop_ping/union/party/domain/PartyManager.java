package com.develop_ping.union.party.domain;

import com.develop_ping.union.party.domain.dto.PartyInfo;
import com.develop_ping.union.party.domain.entity.Party;

public interface PartyManager {

    PartyInfo createParty(Long gatheringId, Long userId);
    PartyInfo findByGatheringId(Long gatheringId);
    Party findOwnerByGatheringId(Long gatheringId);
}
