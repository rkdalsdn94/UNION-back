package com.develop_ping.union.party.domain;

import com.develop_ping.union.party.domain.dto.PartyInfo;

public interface PartyManager {

    PartyInfo createParty(Long gatheringId, Long userId);
}
