package com.develop_ping.union.gathering.presentation;

import com.develop_ping.union.gathering.domain.SortType;
import com.develop_ping.union.gathering.domain.dto.request.GatheringListCommand;
import com.develop_ping.union.gathering.domain.service.GatheringService;
import com.develop_ping.union.gathering.presentation.dto.request.GatheringRequest;
import com.develop_ping.union.gathering.presentation.dto.response.GatheringDetailResponse;
import com.develop_ping.union.gathering.presentation.dto.response.GatheringListResponse;
import com.develop_ping.union.gathering.presentation.dto.response.GatheringResponse;
import com.develop_ping.union.user.domain.entity.User;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
        @PageableDefault(size = 3) Pageable pageable,
        @AuthenticationPrincipal User user
    ) {
        log.info("모임 리스트 조회 요청 - sortType: {}, latitude: {}, longitude: {}, pageable: {}", sortType, latitude, longitude, pageable);

        Slice<GatheringListResponse> gatheringList = gatheringService.getGatheringList(
            GatheringListCommand.of(sortType, latitude, longitude, keyword, pageable), user);

        return ResponseEntity.ok(gatheringList);
    }

    @PostMapping
    public ResponseEntity<Long> createGathering(
        @AuthenticationPrincipal User user,
        @Valid @RequestBody GatheringRequest request
    ) {
        GatheringResponse gathering = gatheringService.createGathering(request.toCommand(), user);
        return ResponseEntity.ok(gathering.getId());
    }

    @GetMapping("/{gatheringId}")
    public ResponseEntity<GatheringDetailResponse> getGatheringDetail(
        @AuthenticationPrincipal User user,
        @PathVariable("gatheringId") Long gatheringId
    ) {
        return ResponseEntity.ok( gatheringService.getGatheringDetail(gatheringId, user));
    }

    @PutMapping("/{gatheringId}")
    public ResponseEntity<Void> updateGathering(
        @AuthenticationPrincipal User user,
        @PathVariable("gatheringId") Long gatheringId,
        @Valid @RequestBody GatheringRequest request
    ) {
        gatheringService.updateGathering(gatheringId, request.toCommand(), user);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{gatheringId}")
    public ResponseEntity<Void> deleteGathering(
        @AuthenticationPrincipal User user,
        @PathVariable("gatheringId") Long gatheringId
    ) {
        gatheringService.deleteGathering(gatheringId, user);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{gatheringId}/participants")
    public ResponseEntity<Void> joinGathering(
        @AuthenticationPrincipal User user,
        @PathVariable("gatheringId") Long gatheringId
    ) {
        gatheringService.joinGathering(gatheringId, user);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{gatheringId}/exit")
    public ResponseEntity<Void> exitGathering(
        @AuthenticationPrincipal User user,
        @PathVariable("gatheringId") Long gatheringId
    ) {
        gatheringService.exitGathering(gatheringId, user);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/my")
    public ResponseEntity<Slice<GatheringListResponse>> getMyGatheringList(
        @AuthenticationPrincipal User user,
        @PageableDefault(size = 5) Pageable pageable
    ) {
        return ResponseEntity.ok( gatheringService.getMyGatheringList(user, pageable));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<Slice<GatheringListResponse>> getUserGatheringList(
        @PathVariable("userId") Long userId,
        @PageableDefault(size = 5) Pageable pageable
    ) {
        return ResponseEntity.ok( gatheringService.getUserGatheringList(userId, pageable));
    }

    @PostMapping("/{gatheringId}/recruited")
    public ResponseEntity<GatheringResponse> recruitedGathering(
        @AuthenticationPrincipal User user,
        @PathVariable("gatheringId") Long gatheringId
    ) {
        return ResponseEntity.ok( gatheringService.recruitedGathering(gatheringId, user));
    }

    @PostMapping("/{gatheringId}/{userId}/kick-out")
    public ResponseEntity<Void> kickOutUser(
        @PathVariable("userId") Long userId,
        @PathVariable("gatheringId") Long gatheringId
    ) {
        User user = User.builder()
            .id(userId)
            .build();

        gatheringService.kickOutUser(userId, gatheringId, user);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/my/participated")
    public List<GatheringListResponse> getParticipatedGatheringList(@AuthenticationPrincipal User user) {
        return gatheringService.getParticipatedGatheringList(user);
    }
}
