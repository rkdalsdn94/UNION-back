package com.develop_ping.union.gathering.infra;

import com.develop_ping.union.gathering.domain.GatheringManager;
import com.develop_ping.union.gathering.domain.GatheringSortStrategy;
import com.develop_ping.union.gathering.domain.dto.request.GatheringListCommand;
import com.develop_ping.union.gathering.domain.dto.response.GatheringInfo;
import com.develop_ping.union.gathering.domain.entity.Gathering;
import com.develop_ping.union.gathering.exception.GatheringNotFoundException;
import com.develop_ping.union.user.domain.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
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
        log.info("모임 생성 ManagerImpl 클래스 : {}", gathering);

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
        log.info("모임 리스트 조회 ManagerImpl 클래스 : {}", command);

        return strategy.applySort(gatheringRepository, command, command.getPageable());
    }

    @Override
    public Gathering findById(Long gatheringId) {
        log.info("모임 단순 조회 (findById) ManagerImpl 클래스 : {}", gatheringId);

        return gatheringRepository.findById(gatheringId)
                                  .orElseThrow(() -> new GatheringNotFoundException(gatheringId));
    }

    @Override
    public void deleteGathering(Gathering gathering) {
        log.info("모임 삭제 ManagerImpl 클래스 : {}", gathering);

        gatheringRepository.delete(gathering);
    }

    @Override
    public Gathering findWithPessimisticLockById(Long gatheringId) {
        log.info("모임 조회 비관적 락 ManagerImpl 클래스 : {}", gatheringId);

        return gatheringRepository.findWithPessimisticLockById(gatheringId)
                                  .orElseThrow(() -> new GatheringNotFoundException(gatheringId));
    }

    @Override
    public Slice<Gathering> getMyGatheringList(User user, Pageable pageable) {
        log.info("내가 작성한 모임 리스트 조회 ManagerImpl 클래스 : {}", user.getId());

        return gatheringRepository.findByUserAsOwner(user, pageable);
    }
}
