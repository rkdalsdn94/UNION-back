package com.develop_ping.union.gathering.domain.service;

import com.develop_ping.union.gathering.domain.GatheringManager;
import com.develop_ping.union.gathering.domain.dto.request.GatheringCommand;
import com.develop_ping.union.gathering.domain.dto.request.GatheringListCommand;
import com.develop_ping.union.gathering.domain.entity.Gathering;
import com.develop_ping.union.gathering.exception.GatheringNotFoundException;
import com.develop_ping.union.gathering.exception.OwnerCannotExitException;
import com.develop_ping.union.gathering.presentation.dto.response.GatheringDetailResponse;
import com.develop_ping.union.gathering.presentation.dto.response.GatheringListResponse;
import com.develop_ping.union.gathering.presentation.dto.response.GatheringResponse;
import com.develop_ping.union.party.domain.PartyManager;
import com.develop_ping.union.party.domain.entity.Party;
import com.develop_ping.union.party.domain.entity.PartyRole;
import com.develop_ping.union.party.exception.AlreadyJoinedException;
import com.develop_ping.union.party.exception.ParticipationNotFoundException;
import com.develop_ping.union.user.domain.entity.User;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class GatheringServiceImpl implements GatheringService {

    private final GatheringManager gatheringManager;
    private final PartyManager partyManager;

    @Transactional
    public GatheringResponse createGathering(GatheringCommand command, User user) {
        Gathering gathering = command.toEntity();
        GatheringResponse savedGathering = gatheringManager.save(gathering);
        partyManager.createParty(gathering, user);

        return savedGathering;
    }

    @Override
    @Transactional
    public GatheringDetailResponse getGatheringDetail(Long gatheringId, User user) {
        Gathering gathering = gatheringManager.findById(gatheringId);

        // 조회수 증가
        gathering.incrementViews();
        gatheringManager.save(gathering);

        // 모임 주최자 정보
        Party ownerParty = partyManager.findOwnerByGatheringIdAndRole(gatheringId, PartyRole.OWNER)
            .orElseThrow(() -> new GatheringNotFoundException(gatheringId));
        User owner = ownerParty.getUser();
        boolean isOwner = gathering.isOwner(user);

        // 모임 참가 여부
        boolean isJoined = partyManager.existsByGatheringAndUser(gathering, user);

        return GatheringDetailResponse.of(gathering, isOwner, isJoined);
    }

    @Override
    @Transactional
    public void updateGathering(Long gatheringId, GatheringCommand command, User user) {
        Gathering gathering = gatheringManager.findById(gatheringId);

        gathering.validateOwner(user);
        gathering.updateGathering(command.toEntity());
        gatheringManager.save(gathering);
    }

    @Override
    @Transactional(readOnly = true)
    public Slice<GatheringListResponse> getGatheringList(GatheringListCommand command, User user) {
        return gatheringManager.getGatheringList(command, user);
    }

    @Override
    @Transactional
    public void deleteGathering(Long gatheringId, User user) {
        Gathering gathering = gatheringManager.findById(gatheringId);

        gathering.validateOwner(user);
        gatheringManager.deleteGathering(gathering);
    }

    @Override
    @Transactional
    public void joinGathering(Long gatheringId, User user) {
        Gathering gathering = gatheringManager.findWithPessimisticLockById(gatheringId);
        Party party = Party.builder()
            .gathering(gathering)
            .user(user)
            .role(PartyRole.PARTICIPANT)
            .build();

        if (partyManager.existsByGatheringAndUser(gathering, user)) {
            throw new AlreadyJoinedException("이미 해당 모임에 참여하셨습니다.");
        }

        gathering.getParties().add(party);
        gathering.incrementCurrentMember();

        if (gathering.isFull()) {
            gathering.close();
        }

        gatheringManager.save(gathering);
    }

    @Override
    @Transactional
    public void exitGathering(Long gatheringId, User user) {
        // TODO: `모임 참가하기`와 `모임 나가기`가 동시에 발생할 경우 락을 기다리게 되는 상황이 자주 발생할 수 있음 (낙관락으로 해결 가능)
        Gathering gathering = gatheringManager.findWithPessimisticLockById(gatheringId);

        if (gathering.isOwner(user)) {
            throw new OwnerCannotExitException("주최자는 모임에서 나갈 수 없습니다.");
        }

        Party party = partyManager.findByGatheringAndUser(gathering, user)
            .orElseThrow(() -> new ParticipationNotFoundException("참여하지 않은 모임입니다."));

        // 현재 모임 인원 수 감소
        gathering.decrementCurrentMember();
        if (!gathering.isFull()) {
            gathering.open();
        }

        gathering.getParties().remove(party);
        gatheringManager.save(gathering);
    }

    @Override
    @Transactional(readOnly = true)
    public Slice<GatheringListResponse> getMyGatheringList(User user, Pageable pageable) {
        Slice<Gathering> gatheringList = gatheringManager.getMyGatheringList(user, pageable);

        return GatheringListResponse.fromSlice(gatheringList, user.getName());
    }

    @Override
    @Transactional(readOnly = true)
    public Slice<GatheringListResponse> getUserGatheringList(Long userId, Pageable pageable) {
        Slice<Gathering> gatheringList = gatheringManager.getUserGatheringList(userId, pageable);
        String userName = gatheringList.stream()
            .findFirst()
            .map(gathering -> gathering.getUser().getName())
            .orElseThrow(() -> new IllegalArgumentException("유저를 찾을 수 없습니다."));

        return GatheringListResponse.fromSlice(gatheringList, userName);
    }

    @Override
    @Transactional
    public GatheringResponse recruitedGathering(Long gatheringId, User user) {
        Gathering gathering = gatheringManager.findById(gatheringId);
        partyManager.validateOwner(user.getId(), gatheringId);

        gathering.changeRecruitmentStatus();
        gatheringManager.save(gathering);

        return GatheringResponse.of(gathering);
    }

    @Override
    public void kickOutUser(Long userId, Long gatheringId, User user) {
        gatheringManager.kickOutUser(userId, gatheringId, user);
    }

    @Override
    public List<GatheringListResponse> getParticipatedGatheringList(User user) {
        List<Gathering> participatedGatheringList =
            gatheringManager.getParticipatedGatheringList(user);

        return GatheringListResponse.fromList(participatedGatheringList, user.getName());
    }
}
