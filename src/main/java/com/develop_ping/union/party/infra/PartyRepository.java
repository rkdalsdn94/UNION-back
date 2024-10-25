package com.develop_ping.union.party.infra;

import com.develop_ping.union.party.domain.entity.Party;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PartyRepository extends JpaRepository<Party, Long> {

    Optional<Party> findByGatheringIdAndUserId(Long id, Long userId);
}
