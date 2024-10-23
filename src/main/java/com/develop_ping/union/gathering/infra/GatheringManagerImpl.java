package com.develop_ping.union.gathering.infra;

import com.develop_ping.union.gathering.application.dto.GatheringInfo;
import com.develop_ping.union.gathering.domain.Gathering;
import com.develop_ping.union.gathering.domain.GatheringManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class GatheringManagerImpl implements GatheringManager {

    private final GatheringRepository gatheringRepository;

    @Override
    @Transactional
    public GatheringInfo createGathering(Gathering gathering) {
        log.info("모임 ManagerImpl 클래스 : {}", gathering);

        Gathering savedGathering = gatheringRepository.save(gathering);

        // party 테이블의 추가
        // partyRepository.save(Party.builder().gathering(save).build());
        return GatheringInfo.of(savedGathering);
    }
}
