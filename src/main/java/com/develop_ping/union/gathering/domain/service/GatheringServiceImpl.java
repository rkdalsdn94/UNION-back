package com.develop_ping.union.gathering.domain.service;

import com.develop_ping.union.gathering.domain.GatheringManager;
import com.develop_ping.union.gathering.domain.dto.request.GatheringCommand;
import com.develop_ping.union.gathering.domain.dto.request.GatheringListCommand;
import com.develop_ping.union.gathering.domain.dto.response.GatheringDetailInfo;
import com.develop_ping.union.gathering.domain.dto.response.GatheringInfo;
import com.develop_ping.union.gathering.domain.dto.response.GatheringListInfo;
import com.develop_ping.union.gathering.domain.entity.Gathering;
import com.develop_ping.union.gathering.domain.GatheringSortStrategy;
import com.develop_ping.union.gathering.domain.GatheringSortStrategyFactory;
import com.develop_ping.union.gathering.exception.GatheringPermissionDeniedException;
import com.develop_ping.union.gathering.exception.OwnerCannotExitException;
import com.develop_ping.union.party.domain.PartyManager;
import com.develop_ping.union.party.domain.dto.PartyInfo;
import com.develop_ping.union.party.domain.entity.Party;
import com.develop_ping.union.party.domain.entity.PartyRole;
import com.develop_ping.union.party.exception.ParticipationNotFoundException;
import com.develop_ping.union.reaction.domain.ReactionManager;
import com.develop_ping.union.user.domain.UserManager;
import com.develop_ping.union.user.domain.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GatheringServiceImpl implements GatheringService {

    private final GatheringManager gatheringManager;
    private final PartyManager partyManager;
    private final UserManager userManager;
    private final ReactionManager reactionManager;
    private final GatheringSortStrategyFactory strategyFactory;

    @Transactional
    public GatheringInfo createGathering(GatheringCommand command, Long userId) {
        log.info("모임 ServiceImpl 클래스 생성 : {}", command);

        GatheringInfo savedGathering = gatheringManager.save(command.toEntity());
        PartyInfo savedParty = partyManager.createParty(savedGathering.getId(), userId);

        log.info("\n모임 파티 생성 완료 : {}, \n {}", savedGathering, savedParty);
        return savedGathering;
    }

    @Override
    @Transactional
    public GatheringDetailInfo getGatheringDetail(Long gatheringId, Long userId) {
        log.info("모임 상세 조회 ServiceImpl 클래스 : {}", gatheringId);

        // 1. 모임 정보 가져오기
        GatheringInfo gatheringInfo = gatheringManager.getGatheringDetail(gatheringId);

        // 2. 요청한 사용자가 주최자인지 확인
        boolean isOwner = partyManager.existsByGatheringIdAndUserIdAndRole(gatheringId, userId, PartyRole.OWNER);

        // 3. 주최자의 닉네임 조회
        String ownerNickname = partyManager.findOwnerNicknameByGatheringId(gatheringId);

        // 4. 좋아요 수 조회
        Long likeCount = reactionManager.selectLikeCount(gatheringId);

        // 5. GatheringDetailInfo 생성 및 반환
        return buildGatheringDetailInfo(gatheringInfo, ownerNickname, likeCount, isOwner);
    }

    @Override
    @Transactional
    public GatheringInfo updateGathering(Long gatheringId, GatheringCommand command, Long userId) {
        log.info("모임 수정 updateGathering ServiceImpl 클래스 : gatheringId {}, command: {}, userId {}", gatheringId, command, userId);

        Long ownerUserId = getOwnerUserId(gatheringId);
        User user = userManager.findById(userId);

        validateGatheringOwner(ownerUserId, user.getId());

        Gathering gathering = gatheringManager.findById(gatheringId);
        gathering.updateGathering(command.toEntity());
        gatheringManager.save(gathering);

        return GatheringInfo.of(gathering);
    }

    @Override
    public Slice<GatheringListInfo> getGatheringList(GatheringListCommand command) {
        log.info("모임 리스트 getGatheringList 조회 ServiceImpl 클래스 : {}", command);

        GatheringSortStrategy strategy = strategyFactory.getStrategy(command.getSortType());
        Slice<Gathering> gatheringList = gatheringManager.getGatheringList(strategy, command);
        return GatheringListInfo.of(gatheringList);
    }

    @Override
    @Transactional
    public void deleteGathering(Long gatheringId, Long userId) {
        log.info("모임 삭제 deleteGathering ServiceImpl 클래스 : gatheringId {}, userId {}", gatheringId, userId);

        Long ownerUserId = getOwnerUserId(gatheringId);
        User user = userManager.findById(userId);

        validateGatheringOwner(ownerUserId, user.getId());

        partyManager.deleteParty(gatheringId);
        gatheringManager.deleteGathering(gatheringId);
    }

    @Override
    @Transactional
    public void joinGathering(Long gatheringId, Long userId) {
        log.info("모임 참가 joinGathering ServiceImpl 클래스 : gatheringId {}, userId {}", gatheringId, userId);

        partyManager.joinGathering(gatheringId, userId);
    }

    @Override
    @Transactional
    public void exitGathering(Long gatheringId, Long userId) {

        Gathering gathering = gatheringManager.findById(gatheringId);

        if (partyManager.existsByGatheringIdAndUserIdAndRole(gatheringId, userId, PartyRole.OWNER)) {
            throw new OwnerCannotExitException("주최자는 모임에서 나갈 수 없습니다.");
        }


        if (!partyManager.existsByGatheringIdAndUserId(gathering.getId(), userId)) {
            throw new ParticipationNotFoundException("참여하지 않은 모임입니다.");
        }


//        if ()

//        Gathering gathering = gatheringManager.findById(gatheringId);
        gathering.decrementCurrentMember();
        partyManager.deleteByGatheringIdAndUserId(gatheringId, userId);
    }

    // TODO: 추후 도메인으로 이동할 수 메서드가 있는지 확인
    private Long getOwnerUserId(Long gatheringId) {
        Party party = partyManager.findByGatheringId(gatheringId);
        return party.getUserId();
    }

    private GatheringDetailInfo buildGatheringDetailInfo(
        GatheringInfo gatheringInfo, String nickname, Long likeCount, boolean isOwner) {
        return GatheringDetailInfo.of(gatheringInfo, nickname, likeCount, isOwner);
    }

    private void validateGatheringOwner(Long ownerId, Long userId) {
        if (!ownerId.equals(userId)) {
            throw new GatheringPermissionDeniedException(ownerId, userId);
        }
    }
}
