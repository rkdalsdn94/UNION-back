package com.develop_ping.union.gathering.presentation;

import com.develop_ping.union.gathering.domain.dto.GatheringInfo;
import com.develop_ping.union.gathering.domain.service.GatheringService;
import com.develop_ping.union.gathering.presentation.dto.request.GatheringRequest;
import com.develop_ping.union.gathering.presentation.dto.response.GatheringDetailResponse;
import com.develop_ping.union.gathering.presentation.dto.response.GatheringResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
public class GatheringController {

    private final GatheringService gatheringService;

    @GetMapping("/gathering")
    public String getGathering() {
        return "gathering";
    }

    /**
     * 모임 생성 API
     * @param request 모임 생성 요청 DTO
     * @return GatheringResponse
     */
    @PostMapping("/gathering")
    public Long createGathering(
        @Valid @RequestBody GatheringRequest request
    ) {
        log.info("모임 컨트롤러 진입: {}", request);

        // TODO: User 정보 추가해야 됨
        Long userId = 1L;

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
        @PathVariable("gatheringId") Long gatheringId
    ) {
        log.info("모임 상세 조회 컨트롤러 진입: {}", gatheringId);

        GatheringInfo gathering = gatheringService.getGatheringDetail(gatheringId);
        GatheringDetailResponse response = GatheringDetailResponse.of(gathering);

        return ResponseEntity.ok(response);
    }
}
