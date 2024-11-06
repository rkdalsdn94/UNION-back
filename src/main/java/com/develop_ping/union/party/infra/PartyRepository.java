package com.develop_ping.union.party.infra;

import com.develop_ping.union.gathering.domain.entity.Gathering;
import com.develop_ping.union.party.domain.entity.Party;
import com.develop_ping.union.party.domain.entity.PartyRole;
import com.develop_ping.union.user.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PartyRepository extends JpaRepository<Party, Long> {

    Optional<Party> findByGatheringAndUser(Gathering gathering, User user);
    boolean existsByGatheringAndUser(Gathering gathering, User user);
    Optional<Party> findByGatheringIdAndRole(Long gatheringId, PartyRole partyRole);

    List<Party> findByUserId(Long userId);
}
