package com.develop_ping.union.party.domain;

import com.develop_ping.union.gathering.domain.entity.Gathering;
import com.develop_ping.union.party.domain.dto.PartyInfo;
import com.develop_ping.union.party.domain.entity.Party;
import com.develop_ping.union.party.domain.entity.PartyRole;
import com.develop_ping.union.user.domain.entity.User;

import java.util.List;
import java.util.Optional;

public interface PartyManager {

    List<Party> findByGatheringId(Long gatheringId);
    PartyInfo createParty(Gathering gathering, User user);
    boolean existsByGatheringAndUser(Gathering gathering, User user);

    Optional<Party> findByGatheringAndUser(Gathering gathering, User user);

    void save(Party party);

    Optional<Party> findOwnerByGatheringIdAndRole(Long gatheringId, PartyRole partyRole);

    Party validateOwner(Long userId, Long gatheringId);

    List<Party> findByUserId(Long userId);

    Party findByGatheringIdAndUserID(Long gatheringId, Long userId);
}
