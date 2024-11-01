package com.develop_ping.union.gathering.domain.service;

import com.develop_ping.union.gathering.domain.GatheringManager;
import com.develop_ping.union.gathering.domain.GatheringSortStrategy;
import com.develop_ping.union.gathering.domain.GatheringSortStrategyFactory;
import com.develop_ping.union.gathering.domain.dto.request.GatheringCommand;
import com.develop_ping.union.gathering.domain.dto.request.GatheringListCommand;
import com.develop_ping.union.gathering.domain.dto.response.GatheringDetailInfo;
import com.develop_ping.union.gathering.domain.dto.response.GatheringInfo;
import com.develop_ping.union.gathering.domain.dto.response.GatheringListInfo;
import com.develop_ping.union.gathering.domain.entity.Gathering;
import com.develop_ping.union.gathering.exception.OwnerCannotExitException;
import com.develop_ping.union.party.domain.PartyManager;
import com.develop_ping.union.party.domain.dto.PartyInfo;
import com.develop_ping.union.party.exception.ParticipationNotFoundException;
import com.develop_ping.union.reaction.domain.ReactionManager;
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
    private final ReactionManager reactionManager;
    private final GatheringSortStrategyFactory strategyFactory;

    @Transactional
    public GatheringInfo createGathering(GatheringCommand command, User user) {
        log.info("\n모임 생성 ServiceImpl 클래스 : command: {}, user: {}", command, user.getId());

        Gathering gathering = command.toEntity();
        GatheringInfo savedGathering = gatheringManager.save(gathering);
        PartyInfo savedParty = partyManager.createParty(gathering, user);

        log.info("\n모임 파티 생성 완료 : {}, \n {}", savedGathering, savedParty);
        return savedGathering;
    }

    @Override
    @Transactional
    public GatheringDetailInfo getGatheringDetail(Long gatheringId, User user) {
        log.info("\n모임 상세 조회 ServiceImpl 클래스 : gathering: {}, user: {}", gatheringId, user.getId());

        Gathering gathering = gatheringManager.findById(gatheringId);

        GatheringInfo gatheringInfo = gatheringManager.getGatheringDetail(gathering.getId());
        boolean isOwner = gathering.isOwner(user);
        String ownerNickname = gathering.getOwnerNickname();
        Long likeCount = reactionManager.selectLikeCount(gathering.getId());

        return buildGatheringDetailInfo(gatheringInfo, ownerNickname, likeCount, isOwner);
    }

    @Override
    @Transactional
    public void updateGathering(Long gatheringId, GatheringCommand command, User user) {
        log.info("\n모임 수정 updateGathering ServiceImpl 클래스 : gathering {}, command: {}, user {}", gatheringId, command, user.getId());

        Gathering gathering = gatheringManager.findById(gatheringId);

        gathering.validateOwner(user);
        gathering.updateGathering(command.toEntity());
        gatheringManager.save(gathering);
    }

    @Override
    public Slice<GatheringListInfo> getGatheringList(GatheringListCommand command) {
        log.info("\n모임 리스트 getGatheringList 조회 ServiceImpl 클래스 : {}", command);

        GatheringSortStrategy strategy = strategyFactory.getStrategy(command.getSortType());
        Slice<Gathering> gatheringList = gatheringManager.getGatheringList(strategy, command);
        return GatheringListInfo.of(gatheringList);
    }

    @Override
    @Transactional
    public void deleteGathering(Long gatheringId, User user) {
        log.info("\n모임 삭제 deleteGathering ServiceImpl 클래스 : gatheringId {}, userId {}", gatheringId, user.getId());

        Gathering gathering = gatheringManager.findById(gatheringId);

        gathering.validateOwner(user);
        gatheringManager.deleteGathering(gathering);
    }

    @Override
    @Transactional
    public void joinGathering(Long gatheringId, User user) {
        log.info("모임 참가 joinGathering ServiceImpl 클래스 : gatheringId {}, user {}", gatheringId, user.getId());

        Gathering gathering = gatheringManager.findWithPessimisticLockById(gatheringId);
        partyManager.joinGathering(gathering, user);
    }

    @Override
    @Transactional
    public void exitGathering(Long gatheringId, User user) {
        log.info("\n모임 나가기 exitGathering ServiceImpl 클래스 : gatheringID {}, user {}", gatheringId, user.getId());

        Gathering gathering = gatheringManager.findById(gatheringId);

        if (gathering.isOwner(user)) {
            throw new OwnerCannotExitException("주최자는 모임에서 나갈 수 없습니다.");
        }

        if (!partyManager.existsByGatheringAndUser(gathering, user)) {
            throw new ParticipationNotFoundException("참여하지 않은 모임입니다.");
        }

        gathering.decrementCurrentMember();
        gatheringManager.deleteGathering(gathering);
    }

    private GatheringDetailInfo buildGatheringDetailInfo(
        GatheringInfo gatheringInfo, String nickname, Long likeCount, boolean isOwner) {
        return GatheringDetailInfo.of(gatheringInfo, nickname, likeCount, isOwner);
    }
}
