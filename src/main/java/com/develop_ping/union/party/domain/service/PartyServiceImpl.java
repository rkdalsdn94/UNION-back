package com.develop_ping.union.party.domain.service;

import com.develop_ping.union.party.domain.PartyManager;
import com.develop_ping.union.party.domain.entity.Party;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PartyServiceImpl implements PartyService {

    private final PartyManager partyManager;

    public List<Party> findByUserId(Long userId) {
        log.info("\n파티 조회 ServiceImpl 클래스 : userId: {}", userId);

        return partyManager.findByUserId(userId);
    }

    @Override
    public List<Party> findByGatheringId(Long gatheringId) {
        log.info("\n파티 조회 ServiceImpl 클래스 : gatheringId: {}", gatheringId);

        return partyManager.findByGatheringId(gatheringId);
    }
}
