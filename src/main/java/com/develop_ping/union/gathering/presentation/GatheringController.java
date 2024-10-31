package com.develop_ping.union.gathering.presentation;

import com.amazonaws.Response;
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

@Slf4j
@RequiredArgsConstructor
@RestController
public class GatheringController {

    private final GatheringService gatheringService;

    @GetMapping("/gathering")
    public Slice<GatheringListResponse> getGathering(
        @RequestParam(value = "sortType", defaultValue = "LATEST") SortType sortType,
        @RequestParam(value = "latitude", defaultValue = "37.556016") Double latitude,
        @RequestParam(value = "longitude", defaultValue = "126.972355") Double longitude,
        @PageableDefault(size = 3, page = 0) Pageable pageable
    ) {
        log.info("모임 리스트 조회 컨트롤러 진입 - sortType: {}, latitude: {}, longitude: {}, pageable: {}",
            sortType, latitude, longitude, pageable);

        Slice<GatheringListInfo> gatheringList = gatheringService.getGatheringList(
            GatheringListCommand.of(sortType, latitude, longitude, pageable)
        );

        return ResponseEntity.ok(gatheringList.map(GatheringListResponse::from)).getBody();
    }

    @PostMapping("/gathering")
    public Long createGathering(
        @AuthenticationPrincipal User user,
        @Valid @RequestBody GatheringRequest request
    ) {
        log.info("모임 생성 컨트롤러 진입: {}", request);

        Long userId = user.getId();
        GatheringInfo gathering = gatheringService.createGathering(request.toCommand(), userId);
        GatheringResponse response = GatheringResponse.of(gathering);

        return ResponseEntity.ok(response.getId()).getBody();
    }

    @GetMapping("/gathering/{gatheringId}")
    public ResponseEntity<GatheringDetailResponse> getGatheringDetail(
        @AuthenticationPrincipal User user,
        @PathVariable("gatheringId") Long gatheringId
    ) {
        log.info("모임 상세 조회 컨트롤러 진입: {}", gatheringId);

        Long userId = user.getId();
        GatheringDetailInfo gatheringDetailInfo = gatheringService.getGatheringDetail(gatheringId, userId);
        GatheringDetailResponse response = GatheringDetailResponse.of(gatheringDetailInfo);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/gathering/{gatheringId}")
    public ResponseEntity<Void> updateGathering(
        @AuthenticationPrincipal User user,
        @PathVariable("gatheringId") Long gatheringId,
        @Valid @RequestBody GatheringRequest request
    ) {
        log.info("모임 수정 컨트롤러 진입: {}", gatheringId);

        Long userId = user.getId();
        gatheringService.updateGathering(gatheringId, request.toCommand(), userId);

        log.info("모임 수정 완료: {}", gatheringId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/gathering/{gatheringId}")
    public ResponseEntity<Void> deleteGathering(
        @AuthenticationPrincipal User user,
        @PathVariable("gatheringId") Long gatheringId
    ) {
        log.info("모임 삭제 컨트롤러 진입: {}", gatheringId);

        Long userId = user.getId();
        gatheringService.deleteGathering(gatheringId, userId);

        return ResponseEntity.noContent().build();
    }
}
