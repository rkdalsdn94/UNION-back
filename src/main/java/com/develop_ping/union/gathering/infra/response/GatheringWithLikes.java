package com.develop_ping.union.gathering.infra.response;

import com.develop_ping.union.gathering.domain.entity.Gathering;
import lombok.Getter;

@Getter
public class GatheringWithLikes {

    private final Gathering gathering;
    private final Long likes;

    public GatheringWithLikes(Gathering gathering, Long likes) {
        this.gathering = gathering;
        this.likes = likes;
    }
}
