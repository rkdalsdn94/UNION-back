package com.develop_ping.union.gathering.infra;

import com.develop_ping.union.gathering.domain.GatheringManager;
import com.develop_ping.union.gathering.domain.GatheringSortStrategy;
import com.develop_ping.union.gathering.domain.dto.request.GatheringListCommand;
import com.develop_ping.union.gathering.domain.entity.Gathering;
import com.develop_ping.union.gathering.exception.GatheringNotFoundException;
import com.develop_ping.union.gathering.presentation.dto.response.GatheringListResponse;
import com.develop_ping.union.gathering.presentation.dto.response.GatheringResponse;
import com.develop_ping.union.party.domain.PartyManager;
import com.develop_ping.union.party.domain.entity.Party;
import com.develop_ping.union.party.domain.entity.PartyRole;
import com.develop_ping.union.party.exception.ParticipationNotFoundException;
import com.develop_ping.union.user.domain.entity.User;
import com.develop_ping.union.user.infra.UserRepository;
import java.util.List;
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
    private final GatheringSortStrategy dynamicSortStrategy;
    private final UserRepository userRepository;

    @Override
    public GatheringResponse save(Gathering gathering) {
        Gathering savedGathering = gatheringRepository.save(gathering);
        return GatheringResponse.of(savedGathering);
    }

    @Override
    public Slice<GatheringListResponse> getGatheringList(GatheringListCommand command, User user) {
        // DynamicSortStrategy를 사용해 정렬과 검색 쿼리 실행 (동적 쿼리 사용, QueryDSL 사용)
        Slice<Gathering> gatheringList = dynamicSortStrategy.applySort(gatheringRepository, user,
            command, command.getPageable());

        return gatheringList.map(
            gathering -> {Party owner = partyManager
                .findOwnerByGatheringIdAndRole(gathering.getId(), PartyRole.OWNER)
                .orElseThrow(() -> new ParticipationNotFoundException("모임 주최자를 찾을 수 없습니다."));
            return GatheringListResponse.from(gathering, owner.getUser().getName());
        });
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

    @Override
    public Slice<Gathering> getMyGatheringList(User user, Pageable pageable) {

        return gatheringRepository.findByUserAsOwner(user, pageable);
    }

    @Override
    public Slice<Gathering> getUserGatheringList(Long userId, Pageable pageable) {

        User findByTokenUserResult = userRepository.findById(userId)
            .orElseThrow(() -> new IllegalArgumentException("유저를 찾을 수 없습니다."));

        return gatheringRepository.findByUserAsOwner(findByTokenUserResult, pageable);
    }

    @Override
    @Transactional
    public void kickOutUser(Long userId, Long gatheringId, User user) {

        User targetUser = userRepository.findById(userId)
            .orElseThrow(() -> new IllegalArgumentException("유저를 찾을 수 없습니다."));

        Gathering gathering = gatheringRepository.findById(gatheringId)
            .orElseThrow(() -> new GatheringNotFoundException(gatheringId));
        gathering.validateOwner(user);

        Party party = partyManager.findByGatheringAndUser(gathering, targetUser)
            .orElseThrow(() -> new ParticipationNotFoundException("참가자를 찾을 수 없습니다."));

        gathering.getParties().remove(party);
        gathering.decrementCurrentMember();
        gatheringRepository.save(gathering);
    }

    @Override
    public List<Gathering> getParticipatedGatheringList(User user) {
        return gatheringRepository.findGatheringsByUserId(user.getId());
    }
}
