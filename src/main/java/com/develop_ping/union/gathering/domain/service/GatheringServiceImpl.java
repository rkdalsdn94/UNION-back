package com.develop_ping.union.gathering.domain.service;

import com.develop_ping.union.gathering.domain.dto.GatheringInfo;
import com.develop_ping.union.gathering.domain.GatheringManager;
import com.develop_ping.union.gathering.domain.dto.GatheringCommand;
import com.develop_ping.union.party.domain.PartyManager;
import com.develop_ping.union.party.domain.dto.PartyInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class GatheringServiceImpl implements GatheringService {

    private final GatheringManager gatheringManager;
    private final PartyManager partyManager;

    @Transactional
    public GatheringInfo createGathering(GatheringCommand command, Long userId) {
        log.info("모임 ServiceImpl 클래스 생성 : {}", command);

        GatheringInfo savedGathering = gatheringManager.createGathering(command.toEntity());
        PartyInfo savedParty = partyManager.createParty(savedGathering.getId(), userId);

        log.info("\n모임 생성 완료 로그: {}", savedGathering);
        log.info("\n파티 생성 완료 로그: {}", savedParty);
        return savedGathering;
    }

    @Override
    public GatheringInfo getGatheringDetail(Long gatheringId) {
        log.info("모임 상세 조회 ServiceImpl 클래스 : {}", gatheringId);

        return gatheringManager.getGatheringDetail(gatheringId);
    }

    @Override
    public GatheringInfo updateGathering(Long gatheringId, GatheringCommand command) {
        return null;
    }
}
