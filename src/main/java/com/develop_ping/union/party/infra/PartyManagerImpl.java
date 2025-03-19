package com.develop_ping.union.party.infra;

import com.develop_ping.union.gathering.domain.entity.Gathering;
import com.develop_ping.union.gathering.exception.GatheringNotFoundException;
import com.develop_ping.union.gathering.exception.GatheringPermissionDeniedException;
import com.develop_ping.union.party.domain.PartyManager;
import com.develop_ping.union.party.domain.dto.PartyInfo;
import com.develop_ping.union.party.domain.entity.Party;
import com.develop_ping.union.party.domain.entity.PartyRole;
import com.develop_ping.union.user.domain.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class PartyManagerImpl implements PartyManager {

    private final PartyRepository partyRepository;

    @Override
    public List<Party> findByGatheringId(Long gatheringId) {
        log.info("파티 조회 요청 - gatheringId: {}", gatheringId);

        return Optional.ofNullable(partyRepository.findByGatheringId(gatheringId))
                .orElse(Collections.emptyList());
    }

    @Override
    public void createParty(Gathering gathering, User user) {
        Party savedParty = partyRepository.save(
            Party.builder()
                 .user(user)
                 .gathering(gathering)
                 .role(PartyRole.OWNER)
                 .build()
        );
        PartyInfo.of(savedParty);
    }

    @Override
    public boolean existsByGatheringAndUser(Gathering gathering, User user) {
        return partyRepository.existsByGatheringAndUser(gathering, user);
    }

    @Override
    public Optional<Party> findByGatheringAndUser(Gathering gathering, User user) {
        return partyRepository.findByGatheringAndUser(gathering, user);
    }

    @Override
    public void save(Party party) {
        partyRepository.save(party);
    }

    @Override
    public Optional<Party> findOwnerByGatheringIdAndRole(Long gatheringId, PartyRole partyRole) {
        return Optional.ofNullable(partyRepository.findByGatheringIdAndRole(gatheringId, partyRole)
                                                  .orElseThrow(() -> new GatheringNotFoundException(gatheringId)));
    }

    @Override
    public Party validateOwner(Long userId, Long gatheringId) {
        Party party = findByGatheringIdAndUserID(gatheringId, userId);

        if (!party.getUser().getId().equals(userId)) {
            throw new GatheringPermissionDeniedException(gatheringId, userId);
        }

        return party;
    }

    @Override
    public List<Party> findByUserId(Long userId) {
        return partyRepository.findByUserId(userId);
    }

    @Override
    public Party findByGatheringIdAndUserID(Long gatheringId, Long userId) {
        return partyRepository.findByGatheringIdAndUserId(gatheringId, userId)
                              .orElseThrow(() -> new GatheringPermissionDeniedException(gatheringId, userId));
    }

    @Override
    @Transactional(readOnly = true)
    public long countByUserIdAndRole(Long userId) {
        return partyRepository.countByUserIdAndRole(userId, PartyRole.OWNER);
    }
}
