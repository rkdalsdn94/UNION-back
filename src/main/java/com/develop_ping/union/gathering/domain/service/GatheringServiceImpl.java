package com.develop_ping.union.gathering.domain.service;

import com.develop_ping.union.gathering.domain.GatheringManager;
import com.develop_ping.union.gathering.domain.dto.request.GatheringCommand;
import com.develop_ping.union.gathering.domain.dto.request.GatheringListCommand;
import com.develop_ping.union.gathering.domain.dto.response.GatheringDetailInfo;
import com.develop_ping.union.gathering.domain.dto.response.GatheringInfo;
import com.develop_ping.union.gathering.domain.dto.response.GatheringListInfo;
import com.develop_ping.union.gathering.domain.entity.Gathering;
import com.develop_ping.union.gathering.domain.strategy.GatheringSortStrategy;
import com.develop_ping.union.gathering.domain.strategy.GatheringSortStrategyFactory;
import com.develop_ping.union.party.domain.PartyManager;
import com.develop_ping.union.party.domain.dto.PartyInfo;
import com.develop_ping.union.reaction.domain.ReactionManager;
import com.develop_ping.union.user.domain.UserManager;
import com.develop_ping.union.user.domain.entity.User;
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
    private final UserManager userManager;
    private final ReactionManager reactionManager;
    private final GatheringSortStrategyFactory strategyFactory;

    @Transactional
    public GatheringInfo createGathering(GatheringCommand command, Long userId) {
        log.info("모임 ServiceImpl 클래스 생성 : {}", command);

        GatheringInfo savedGathering = gatheringManager.createGathering(command.toEntity());
        PartyInfo savedParty = partyManager.createParty(savedGathering.getId(), userId);

        log.info("\n모임 파티 생성 완료 : {}, \n {}", savedGathering, savedParty);
        return savedGathering;
    }

    @Override
    public GatheringDetailInfo getGatheringDetail(Long gatheringId, Long userId) {
        log.info("모임 상세 조회 ServiceImpl 클래스 : {}", gatheringId);

        // 모임 정보
        GatheringInfo gatheringInfo = gatheringManager.getGatheringDetail(gatheringId);
        PartyInfo partyResponse = partyManager.findByGatheringId(gatheringId);

        // 모임 주최자 정보
        Long ownerId = partyManager.findOwnerByGatheringId(gatheringId);
        Long ownerUserId = partyResponse.getUserId();
        boolean isOwner = isOwnerCheck(ownerId, userId);

        // 좋아요 수
        Long likeCount = reactionManager.selectLikeCount(gatheringId);

        // 주최자 닉네임
        User userResponse = userManager.findById(ownerUserId);
        String nickname = userResponse.getNickname();

        return GatheringDetailInfo.builder()
                                  .gatheringInfo(gatheringInfo)
                                  .userNickname(nickname)
                                  .likes(likeCount)
                                  .isOwner(isOwner)
                                  .build();
    }

    private boolean isOwnerCheck(Long ownerId, Long userId) {
        return ownerId.equals(userId);
    }

    @Override
    public GatheringInfo updateGathering(Long gatheringId, GatheringCommand command) {
        return null;
    }

    @Override
    public Slice<GatheringListInfo> getGatheringList(
        GatheringListCommand command, Pageable pageable
    ) {
        log.info("모임 리스트 조회 ServiceImpl 클래스 : {}", command);

        GatheringSortStrategy strategy = strategyFactory.getStrategy(command.getSortType());
        Slice<Gathering> gatheringList = gatheringManager.getGatheringList(strategy, command, pageable);
        return GatheringListInfo.of(gatheringList);
    }
}
