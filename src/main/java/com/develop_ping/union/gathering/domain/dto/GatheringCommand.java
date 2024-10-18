package com.develop_ping.union.gathering.domain.dto;

import com.develop_ping.union.gathering.domain.Gathering;

public class GatheringCommand {

    public Gathering toEntity() {
        return Gathering.builder()
            .title("title")
            .content("content")
                        .build();
    }
}
