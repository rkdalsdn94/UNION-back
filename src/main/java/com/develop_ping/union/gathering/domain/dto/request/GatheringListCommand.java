package com.develop_ping.union.gathering.domain.dto.request;

import com.develop_ping.union.gathering.domain.SortType;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Pageable;

@Getter
public class GatheringListCommand {

    private final SortType sortType;
    private final Double latitude;
    private final Double longitude;
    private final Pageable pageable;

    @Builder
    private GatheringListCommand(SortType sortType, Double latitude, Double longitude, Pageable pageable) {
        this.sortType = sortType;
        this.latitude = latitude;
        this.longitude = longitude;
        this.pageable = pageable;
    }
}
