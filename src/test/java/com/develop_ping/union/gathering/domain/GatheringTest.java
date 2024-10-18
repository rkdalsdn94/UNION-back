package com.develop_ping.union.gathering.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class GatheringTest {

    @DisplayName("모임을 생성할 때 제목은 필수값이다.")
    @Test
    void createGatheringWithTitle() {
        // given
        Gathering request = Gathering.builder()
                                       .content("content")
                                       .gatheringDateTime(ZonedDateTime.parse("2021-10-10 10:10:10"))
                                       .maxMember(5)
                                       .token("token")
                                       .build();

        // when
        Gathering response = request.createGathering(request);

        // then
        assertThrows(IllegalArgumentException.class, () -> {
            response.(request.getTitle());
        });
    }
}
