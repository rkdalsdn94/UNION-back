package com.develop_ping.union.gathering.presentation;

import com.develop_ping.union.gathering.domain.dto.GatheringDetailInfo;
import com.develop_ping.union.gathering.domain.dto.GatheringInfo;
import com.develop_ping.union.gathering.domain.service.GatheringService;
import com.develop_ping.union.gathering.presentation.dto.request.GatheringRequest;
import com.develop_ping.union.gathering.presentation.dto.response.GatheringDetailResponse;
import com.develop_ping.union.gathering.presentation.dto.response.GatheringListResponse;
import com.develop_ping.union.gathering.presentation.dto.response.GatheringResponse;
import com.develop_ping.union.user.domain.entity.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
public class GatheringController {

    private final GatheringService gatheringService;

    @GetMapping("/gathering")
    public List<GatheringListResponse> getGathering() {
        return null;
//        return "gathering";
    }

    /**
     * 모임 생성 API
     * @param request 모임 생성 요청 DTO
     * @return GatheringResponse
     */
    @PostMapping("/gathering")
    public Long createGathering(
        @AuthenticationPrincipal User user,
        @Valid @RequestBody GatheringRequest request
    ) {
        log.info("모임 컨트롤러 진입: {}", request);

        Long userId = user.getId();
        GatheringInfo gathering = gatheringService.createGathering(request.toCommand(), userId);
        GatheringResponse response = GatheringResponse.of(gathering);

        return ResponseEntity.ok(response.getId()).getBody();
    }

    /**
     * 모임 상세 조회 API
     * @param gatheringId 모임 ID
     * @return GatheringResponse 모임 상세 DTO
     */
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
}
