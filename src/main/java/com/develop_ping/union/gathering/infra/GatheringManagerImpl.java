package com.develop_ping.union.gathering.infra;

import com.develop_ping.union.gathering.domain.GatheringManager;
import com.develop_ping.union.gathering.domain.GatheringSortStrategy;
import com.develop_ping.union.gathering.domain.dto.request.GatheringListCommand;
import com.develop_ping.union.gathering.domain.dto.response.GatheringInfo;
import com.develop_ping.union.gathering.domain.entity.Gathering;
import com.develop_ping.union.gathering.exception.GatheringNotFoundException;
import com.develop_ping.union.party.domain.PartyManager;
import com.develop_ping.union.party.domain.entity.Party;
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
    private final PartyManager partyManager;

    @Override
    public GatheringInfo save(Gathering gathering) {
        log.info("\n모임 ManagerImpl 클래스 save 호출 (update, create) : {}", gathering);

        Gathering savedGathering = gatheringRepository.save(gathering);
        return GatheringInfo.of(savedGathering);
    }

    @Override
    public Slice<Gathering> getGatheringList(
        GatheringSortStrategy strategy, GatheringListCommand command
    ) {
        log.info("\n모임 리스트 조회 ManagerImpl 클래스 : {}", command);

        return strategy.applySort(gatheringRepository, command, command.getPageable());
    }

    @Override
    public Gathering findById(Long gatheringId) {
        log.info("\n모임 단순 조회 (findById) ManagerImpl 클래스 : {}", gatheringId);

        return gatheringRepository.findById(gatheringId)
                                  .orElseThrow(() -> new GatheringNotFoundException(gatheringId));
    }

    @Override
    public void deleteGathering(Gathering gathering) {
        log.info("\n모임 삭제 ManagerImpl 클래스 : {}", gathering);

        gatheringRepository.delete(gathering);
    }

    @Override
    public Gathering findWithPessimisticLockById(Long gatheringId) {
        log.info("\n모임 조회 비관적 락 ManagerImpl 클래스 : {}", gatheringId);

        return gatheringRepository.findWithPessimisticLockById(gatheringId)
                                  .orElseThrow(() -> new GatheringNotFoundException(gatheringId));
    }

    @Override
    public Slice<Gathering> getMyGatheringList(User user, Pageable pageable) {
        log.info("\n내가 작성한 모임 리스트 조회 ManagerImpl 클래스 : {}", user.getId());

        return gatheringRepository.findByUserAsOwner(user, pageable);
    }
}
