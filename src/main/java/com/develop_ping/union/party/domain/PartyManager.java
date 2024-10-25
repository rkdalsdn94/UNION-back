package com.develop_ping.union.party.domain;

import com.develop_ping.union.party.domain.dto.PartyInfo;

public interface PartyManager {

    PartyInfo createParty(Long gatheringId, Long userId);

    PartyInfo findByGatheringId(Long gatheringId);

    // 파티 참여자 정보 구하는 API
    Long findOwnerByGatheringId(Long gatheringId);
}
