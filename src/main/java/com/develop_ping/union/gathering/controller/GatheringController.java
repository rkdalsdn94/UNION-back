package com.develop_ping.union.gathering.controller;

import com.develop_ping.union.gathering.application.GatheringService;
import com.develop_ping.union.gathering.application.dto.GatheringInfo;
import com.develop_ping.union.gathering.controller.dto.GatheringRequest;
import com.develop_ping.union.gathering.domain.Gathering;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class GatheringController {

    private final GatheringService gatheringService;

    @GetMapping("/gathering")
    public String getGathering() {
        return "gathering";
    }

    @PostMapping("/gathering")
    public ResponseEntity<GatheringInfo> createGathering(@Valid @RequestBody GatheringRequest request) {
        log.info("모임 컨트롤러 진입: {}", request);

        // TODO: 필요하다면 GatheringInfo에서 GatheringResponse로 변경해야 됨
        // TODO: User 정보 추가해야 됨
        GatheringInfo gathering = gatheringService.createGathering(request.toCommand());

        return ResponseEntity.ok(gathering);
    }
}
