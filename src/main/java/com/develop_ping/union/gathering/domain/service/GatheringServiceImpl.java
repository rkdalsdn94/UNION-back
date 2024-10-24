package com.develop_ping.union.gathering.domain.service;

import com.develop_ping.union.gathering.domain.dto.GatheringInfo;
import com.develop_ping.union.gathering.domain.GatheringManager;
import com.develop_ping.union.gathering.domain.dto.GatheringCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class GatheringServiceImpl implements GatheringService {

    private final GatheringManager gatheringManager;

    @Transactional
    public GatheringInfo createGathering(GatheringCommand command) {
        log.info("모임 ServiceImpl 클래스 : {}", command);

        // TODO: party 테이블에 User정보 추가해야 됨

        return gatheringManager.createGathering(command.toEntity());
    }
}
