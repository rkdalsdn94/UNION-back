package com.develop_ping.union.party.domain;

import com.develop_ping.union.gathering.domain.entity.Gathering;
import com.develop_ping.union.party.domain.dto.PartyInfo;
import com.develop_ping.union.party.domain.entity.Party;
import com.develop_ping.union.user.domain.entity.User;

import java.util.Optional;

public interface PartyManager {

    PartyInfo createParty(Gathering gathering, User user);
    boolean existsByGatheringAndUser(Gathering gathering, User user);

    // 모임 참여자 삭제 - 나가기 기능
    void exitGathering(Gathering gathering, User user);

    Party findOwnerByGathering(Long gatheringId);

    Optional<Party> findByGatheringAndUser(Gathering gathering, User user);

    void save(Party party);
}
