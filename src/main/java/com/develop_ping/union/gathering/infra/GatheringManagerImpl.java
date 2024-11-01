package com.develop_ping.union.gathering.infra;

import com.develop_ping.union.gathering.domain.GatheringManager;
import com.develop_ping.union.gathering.domain.GatheringSortStrategy;
import com.develop_ping.union.gathering.domain.dto.request.GatheringListCommand;
import com.develop_ping.union.gathering.domain.dto.response.GatheringInfo;
import com.develop_ping.union.gathering.domain.entity.Gathering;
import com.develop_ping.union.gathering.exception.GatheringNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class GatheringManagerImpl implements GatheringManager {

    private final GatheringRepository gatheringRepository;

    @Override
    public GatheringInfo save(Gathering gathering) {
        log.info("모임 ManagerImpl 클래스 : {}", gathering);

        Gathering savedGathering = gatheringRepository.save(gathering);
        return GatheringInfo.of(savedGathering);
    }

    @Override
    public GatheringInfo getGatheringDetail(Long gatheringId) {
        log.info("모임 상세 조회 ManagerImpl 클래스 : {}", gatheringId);

        gatheringRepository.incrementViewCount(gatheringId);
        return GatheringInfo.of(gatheringRepository.findById(gatheringId)
                                                   .orElseThrow(() -> new GatheringNotFoundException(gatheringId)));
    }

    @Override
    public Slice<Gathering> getGatheringList(
        GatheringSortStrategy strategy, GatheringListCommand command
    ) {
        return strategy.applySort(gatheringRepository, command, command.getPageable());
    }

    @Override
    public Gathering findById(Long gatheringId) {
        return gatheringRepository.findById(gatheringId)
                                  .orElseThrow(() -> new GatheringNotFoundException(gatheringId));
    }

    @Override
    public void deleteGathering(Gathering gathering) {
        gatheringRepository.delete(gathering);
    }

    @Override
    public Gathering findWithPessimisticLockById(Long gatheringId) {
        return gatheringRepository.findWithPessimisticLockById(gatheringId)
                                  .orElseThrow(() -> new GatheringNotFoundException(gatheringId));
    }
}
