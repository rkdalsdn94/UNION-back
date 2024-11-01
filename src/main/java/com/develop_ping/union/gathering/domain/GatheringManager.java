package com.develop_ping.union.gathering.domain;

import com.develop_ping.union.gathering.domain.dto.request.GatheringListCommand;
import com.develop_ping.union.gathering.domain.dto.response.GatheringInfo;
import com.develop_ping.union.gathering.domain.entity.Gathering;
import org.springframework.data.domain.Slice;

import java.util.Optional;

public interface GatheringManager {

    GatheringInfo save(Gathering gathering);
    GatheringInfo getGatheringDetail(Long gatheringId);
    Slice<Gathering> getGatheringList(GatheringSortStrategy strategy, GatheringListCommand command);
    Gathering findById(Long gatheringId);
    void deleteGathering(Gathering gathering);
    Gathering findWithPessimisticLockById(Long gatheringId);

    // 유저 탈퇴시
    //  이 유저가 가입된 모임 목록을 조회
    //    유저의 권한이 회원 (파티 나가기)
    //    유저의 권한이 오너 (모임, 파티 삭제) -> 모임 완전 삭제
    // 오너 -> 모임은 보이되 유저 정보가 없는 상태로 보여줘야함
    //
}
