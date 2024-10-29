package com.develop_ping.union.gathering.presentation.dto.request;

import com.develop_ping.union.gathering.domain.SortType;
import com.develop_ping.union.gathering.domain.dto.request.GatheringListCommand;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@Getter
@NoArgsConstructor
public class BackUpGatheringListRequest {
    private SortType sortType = SortType.LATEST;
    private Double latitude = 37.5665;
    private Double longitude = 126.9780;
    private int page = 0; // 기본 페이지
    private int size = 3; // 기본 페이지 크기

    public Pageable toPageable() {
        return PageRequest.of(this.page, this.size);
    }

    public GatheringListCommand toCommand() {
        return GatheringListCommand.builder()
                                   .sortType(sortType)
                                   .latitude(getLatitude())
                                   .longitude(getLongitude())
                                   .pageable(toPageable())
                                   .build();
    }

    @Override
    public String toString() {
        return "GatheringListRequest{" +
            "latitude=" + latitude +
            ", sortType=" + sortType +
            ", longitude=" + longitude +
            ", page=" + page +
            ", size=" + size +
            '}';
    }
}
