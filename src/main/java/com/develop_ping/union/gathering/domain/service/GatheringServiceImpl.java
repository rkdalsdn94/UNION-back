package com.develop_ping.union.gathering.domain.service;

import com.develop_ping.union.gathering.domain.GatheringManager;
import com.develop_ping.union.gathering.domain.dto.GatheringCommand;
import com.develop_ping.union.gathering.domain.dto.GatheringDetailInfo;
import com.develop_ping.union.gathering.domain.dto.GatheringInfo;
import com.develop_ping.union.party.domain.PartyManager;
import com.develop_ping.union.party.domain.dto.PartyInfo;
import com.develop_ping.union.reaction.domain.ReactionManager;
import com.develop_ping.union.user.domain.UserManager;
import com.develop_ping.union.user.domain.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

        GatheringInfo gatheringInfo = gatheringManager.getGatheringDetail(gatheringId);
        Long ownerId = partyManager.findOwnerByGatheringId(gatheringId);
        PartyInfo partyResponse = partyManager.findByGatheringId(gatheringId);
        Long ownerUserId = partyResponse.getUserId();
        User userResponse = userManager.findById(ownerUserId);
        String nickname = userResponse.getNickname();
        boolean isOwner = isOwnerCheck(ownerId, userId);
        Long likeCount = reactionManager.selectLikeCount(gatheringId);

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
}
