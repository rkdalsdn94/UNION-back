package com.develop_ping.union.gathering.domain;

import com.develop_ping.union.gathering.domain.dto.request.GatheringListCommand;
import com.develop_ping.union.gathering.domain.dto.response.GatheringInfo;
import com.develop_ping.union.gathering.domain.dto.response.GatheringListInfo;
import com.develop_ping.union.gathering.domain.entity.Gathering;
import com.develop_ping.union.user.domain.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;

public interface GatheringManager {

    GatheringInfo save(Gathering gathering);
    Slice<GatheringListInfo> getGatheringList(GatheringListCommand command);
    Gathering findById(Long gatheringId);
    void deleteGathering(Gathering gathering);
    Gathering findWithPessimisticLockById(Long gatheringId);

    Slice<Gathering> getMyGatheringList(User user, Pageable pageable);

    Slice<Gathering> getUserGatheringList(String userToken, Pageable pageable);

    void kickOutUser(String userToken, Long gatheringId, User user);

    List<Gathering> getParticipatedGatheringList(User user);

    // 유저 탈퇴시
    //  이 유저가 가입된 모임 목록을 조회
    //    유저의 권한이 회원 (파티 나가기)
    //    유저의 권한이 오너 (모임, 파티 삭제) -> 모임 완전 삭제
    // 오너 -> 모임은 보이되 유저 정보가 없는 상태로 보여줘야함
    //
}
