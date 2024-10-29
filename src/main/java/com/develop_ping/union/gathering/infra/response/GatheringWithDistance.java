package com.develop_ping.union.gathering.infra.response;

import com.develop_ping.union.gathering.domain.entity.Gathering;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GatheringWithDistance {

    private final Gathering gathering;
    private final Double distance;

    public GatheringWithDistance(Gathering gathering, Double distance) {
        this.gathering = gathering;
        this.distance = distance;
    }
}
