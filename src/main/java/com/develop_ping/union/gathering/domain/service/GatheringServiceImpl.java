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
import com.develop_ping.union.party.domain.entity.Party;
import com.develop_ping.union.party.domain.entity.PartyRole;
import com.develop_ping.union.party.exception.AlreadyJoinedException;
import com.develop_ping.union.party.exception.ParticipationNotFoundException;
import com.develop_ping.union.photo.domain.PhotoManager;
import com.develop_ping.union.reaction.domain.ReactionManager;
import com.develop_ping.union.reaction.domain.entity.ReactionType;
import com.develop_ping.union.user.domain.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GatheringServiceImpl implements GatheringService {

    private final GatheringManager gatheringManager;
    private final PartyManager partyManager;
    private final ReactionManager reactionManager;
    private final GatheringSortStrategyFactory strategyFactory;
    private final PhotoManager photoManager;

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

        // 조회수 증가
        gathering.incrementViews();
        gatheringManager.save(gathering);

        // 모임 주최자 정보
        Party ownerParty = partyManager.findOwnerByGatheringIdAndRole(gatheringId, PartyRole.OWNER);
        User owner = ownerParty.getUser();
        boolean isOwner = gathering.isOwner(user);

        // 좋아요 수
        Long likes = reactionManager.selectLikeCount(gathering.getId());

        // 사용자가 좋아요를 눌렀는지 여부
        boolean isLiked = reactionManager.existsByUserIdAndTypeAndId(user.getId(), ReactionType.GATHERING, gatheringId);

        // 모임 사진 정보
        List<String> photos = photoManager.findGatheringPhotos(gatheringId);

        // GatheringInfo 생성
        GatheringInfo gatheringInfo = GatheringInfo.of(gathering);

        return GatheringDetailInfo.builder()
                                  .gatheringInfo(gatheringInfo)
                                  .user(owner)
                                  .likes(likes)
                                  .isOwner(isOwner)
                                  .isLiked(isLiked)
                                  .photos(photos)
                                  .build();
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
        gatheringManager.save(gathering);
    }

    @Override
    @Transactional
    public void exitGathering(Long gatheringId, User user) {
        log.info("\n모임 나가기 (exitGathering) ServiceImpl 클래스 : gatheringID {}, user {}", gatheringId, user.getId());

        Gathering gathering = gatheringManager.findById(gatheringId);

        if (gathering.isOwner(user)) {
            throw new OwnerCannotExitException("주최자는 모임에서 나갈 수 없습니다.");
        }

        Party party = partyManager.findByGatheringAndUser(gathering, user)
                                  .orElseThrow(() -> new ParticipationNotFoundException("참여하지 않은 모임입니다."));

        // 현재 모임 인원 수 감소
        gathering.decrementCurrentMember();
        gathering.getParties().remove(party);
        gatheringManager.save(gathering);
    }

    @Override
    public Slice<GatheringListInfo> getMyGatheringList(User user, Pageable pageable) {
        log.info("\n내가 생성한 모임 리스트 조회 getMyGatheringList ServiceImpl 클래스 : userId {}, pageable {}", user.getId(), pageable);

        Slice<Gathering> gatheringList = gatheringManager.getMyGatheringList(user, pageable);
        return GatheringListInfo.of(gatheringList);
    }
}
