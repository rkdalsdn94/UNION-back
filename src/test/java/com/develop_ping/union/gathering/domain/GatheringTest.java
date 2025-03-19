package com.develop_ping.union.gathering.domain;

import com.develop_ping.union.gathering.domain.entity.Gathering;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class GatheringTest {

    @DisplayName("모임 객체를 만들어 모임을 생성한다.")
    @Test
    void gatheringIsCreatedSuccessfully() {
        // given
        ZonedDateTime gatheringTime = ZonedDateTime.now().plusHours(1);
        Gathering gathering = Gathering.builder()
                                       .title("모임 제목")
                                       .content("모임 내용")
                                       .gatheringDateTime(gatheringTime)
                                       .maxMember(10)
                                       .build();

        // when & then
        assertThat(gathering).isNotNull();
        assertThat(gathering.getTitle()).isEqualTo("모임 제목");
        assertThat(gathering.getContent()).isEqualTo("모임 내용");
        assertThat(gathering.getGatheringDateTime()).isEqualTo(gatheringTime);
        assertThat(gathering.getMaxMember()).isEqualTo(10);
        assertThat(gathering.getCurrentMember()).isEqualTo(1);
    }
}
