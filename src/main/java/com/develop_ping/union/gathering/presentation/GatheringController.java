package com.develop_ping.union.gathering.presentation;

import com.develop_ping.union.gathering.domain.SortType;
import com.develop_ping.union.gathering.domain.dto.request.GatheringListCommand;
import com.develop_ping.union.gathering.domain.dto.response.GatheringDetailInfo;
import com.develop_ping.union.gathering.domain.dto.response.GatheringInfo;
import com.develop_ping.union.gathering.domain.dto.response.GatheringListInfo;
import com.develop_ping.union.gathering.domain.service.GatheringService;
import com.develop_ping.union.gathering.presentation.dto.request.GatheringRequest;
import com.develop_ping.union.gathering.presentation.dto.response.GatheringDetailResponse;
import com.develop_ping.union.gathering.presentation.dto.response.GatheringListResponse;
import com.develop_ping.union.gathering.presentation.dto.response.GatheringResponse;
import com.develop_ping.union.user.domain.entity.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/gatherings")
public class GatheringController {

    private final GatheringService gatheringService;

    @GetMapping
    public ResponseEntity<Slice<GatheringListResponse>> getGatheringList(
        @RequestParam(value = "sortType", defaultValue = "LATEST") SortType sortType,
        @RequestParam(value = "latitude", defaultValue = "37.556016") Double latitude,
        @RequestParam(value = "longitude", defaultValue = "126.972355") Double longitude,
        @RequestParam(value = "keyword", required = false) String keyword,
        @PageableDefault(size = 3) Pageable pageable
    ) {
        log.info("모임 리스트 조회 요청 - sortType: {}, latitude: {}, longitude: {}, pageable: {}", sortType, latitude, longitude, pageable);

        Slice<GatheringListInfo> gatheringList =
            gatheringService
                .getGatheringList(GatheringListCommand.of(sortType, latitude, longitude, keyword, pageable));

        Slice<GatheringListResponse> responses = gatheringList.map(GatheringListResponse::from);

        return ResponseEntity.ok(responses);
    }

    @PostMapping
    public ResponseEntity<Long> createGathering(
        @AuthenticationPrincipal User user,
        @Valid @RequestBody GatheringRequest request
    ) {
        log.info("모임 생성 요청 - userId: {}, request: {}", user.getId(), request);

        GatheringInfo gathering = gatheringService.createGathering(request.toCommand(), user);
        return ResponseEntity.ok(gathering.getId());
    }

    @GetMapping("/{gatheringId}")
    public ResponseEntity<GatheringDetailResponse> getGatheringDetail(
        @AuthenticationPrincipal User user,
        @PathVariable("gatheringId") Long gatheringId
    ) {
        log.info("모임 상세 조회 요청 - gatheringId: {}, userId: {}", gatheringId, user.getId());

        GatheringDetailInfo gatheringDetailInfo = gatheringService.getGatheringDetail(gatheringId, user);
        GatheringDetailResponse response = GatheringDetailResponse.from(gatheringDetailInfo);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{gatheringId}")
    public ResponseEntity<Void> updateGathering(
        @AuthenticationPrincipal User user,
        @PathVariable("gatheringId") Long gatheringId,
        @Valid @RequestBody GatheringRequest request
    ) {
        log.info("모임 수정 요청 - gatheringId: {}, userId: {}", gatheringId, user.getId());

        gatheringService.updateGathering(gatheringId, request.toCommand(), user);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{gatheringId}")
    public ResponseEntity<Void> deleteGathering(
        @AuthenticationPrincipal User user,
        @PathVariable("gatheringId") Long gatheringId
    ) {
        log.info("모임 삭제 요청 - gatheringId: {}, userId: {}", gatheringId, user.getId());

        gatheringService.deleteGathering(gatheringId, user);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{gatheringId}/participants")
    public ResponseEntity<Void> joinGathering(
        @AuthenticationPrincipal User user,
        @PathVariable("gatheringId") Long gatheringId
    ) {
        log.info("모임 가입 요청 - gatheringId: {}, userId: {}", gatheringId, user.getId());

        gatheringService.joinGathering(gatheringId, user);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{gatheringId}/exit")
    public ResponseEntity<Void> exitGathering(
        @AuthenticationPrincipal User user,
        @PathVariable("gatheringId") Long gatheringId
    ) {
        log.info("모임 나가기 요청 - gatheringId: {}, userId: {}", gatheringId, user.getId());

        gatheringService.exitGathering(gatheringId, user);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/my")
    public ResponseEntity<Slice<GatheringListResponse>> getMyGatheringList(
        @AuthenticationPrincipal User user,
        @PageableDefault(size = 5) Pageable pageable
    ) {
        log.info("내가 생성한 모임 리스트 조회 요청 - userId: {}, pageable: {}", user.getId(), pageable);

        Slice<GatheringListInfo> gatheringList = gatheringService.getMyGatheringList(user, pageable);
        Slice<GatheringListResponse> response = gatheringList.map(GatheringListResponse::from);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/user/{userToken}")
    public ResponseEntity<Slice<GatheringListResponse>> getUserGatheringList(
        @PathVariable("userToken") String userToken,
        @PageableDefault(size = 5) Pageable pageable
    ) {
        log.info("특정 사용자의 모임 리스트 조회 요청 - userToken: {}, pageable: {}", userToken, pageable);

        Slice<GatheringListInfo> gatheringList = gatheringService.getUserGatheringList(userToken, pageable);
        Slice<GatheringListResponse> response = gatheringList.map(GatheringListResponse::from);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/{gatheringId}/recruited")
    public ResponseEntity<GatheringResponse> recruitedGathering(
        @AuthenticationPrincipal User user,
        @PathVariable("gatheringId") Long gatheringId
    ) {
        log.info("모임 모집 완료 요청 - gatheringId: {}", gatheringId);

        GatheringInfo gatheringInfo = gatheringService.recruitedGathering(gatheringId, user);
        GatheringResponse response = GatheringResponse.from(gatheringInfo);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/{gatheringId}/{userToken}/kick-out")
    public ResponseEntity<Void> kickOutUser(
        @AuthenticationPrincipal User user,
        @PathVariable("userToken") String userToken,
        @PathVariable("gatheringId") Long gatheringId
    ) {
        log.info("모임 멤버 추방 요청 - gatheringId: {}, userToken: {}, user: {}", gatheringId, userToken, user);

        gatheringService.kickOutUser(userToken, gatheringId, user);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/my/participated")
    public List<GatheringListResponse> getParticipatedGatheringList(@AuthenticationPrincipal User user) {
        log.info("\n내가 참여한 모임 리스트 조회 getParticipatedGatheringList ServiceImpl 클래스 : userId {}", user.getId());

        List<GatheringListInfo> participatedGatheringList = gatheringService.getParticipatedGatheringList(user);
        return participatedGatheringList.stream().map(GatheringListResponse::from).toList();
    }
}
