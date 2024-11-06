package com.develop_ping.union.party.domain.service;

import com.develop_ping.union.party.domain.entity.Party;

import java.util.List;

public interface PartyService {

    List<Party> findByUserId(Long userId);

    List<Party> findByGatheringId(Long gatheringId);
}
