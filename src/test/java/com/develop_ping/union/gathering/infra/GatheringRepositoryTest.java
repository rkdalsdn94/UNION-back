package com.develop_ping.union.gathering.infra;

import com.develop_ping.union.IntegrationTestSupport;
import com.develop_ping.union.gathering.domain.entity.Gathering;
import com.develop_ping.union.gathering.domain.entity.Place;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
class GatheringRepositoryTest extends IntegrationTestSupport {

    @Autowired
    private GatheringRepository gatheringRepository;

    @DisplayName("모임 저장 테스트")
    @Test
    void saveGathering() {
        // given
        Gathering gathering = Gathering.builder()
                                       .title("모임 제목")
                                       .content("모임 내용")
                                       .maxMember(5)
                                       .gatheringDateTime(ZonedDateTime.now().plusHours(2))
                                       .place(new Place("장소", 37.123456, 127.123456))
                                       .build();
        // when
        Gathering savedGathering = gatheringRepository.save(gathering);

        // then
        assertThat(savedGathering).isNotNull();
        assertThat(savedGathering.getId()).isNotNull();
        assertThat(savedGathering.getTitle()).isEqualTo("모임 제목");
        assertThat(savedGathering.getContent()).isEqualTo("모임 내용");
        assertThat(savedGathering.getMaxMember()).isEqualTo(5);
        assertThat(savedGathering.getCurrentMember()).isEqualTo(1);
        assertThat(savedGathering.getGatheringDateTime()).isNotNull();
        assertThat(savedGathering.getPlace()).isNotNull();
        assertThat(savedGathering.getPlace().getAddress()).isEqualTo("장소");
        assertThat(savedGathering.getPlace().getLatitude()).isEqualTo(37.123456);
        assertThat(savedGathering.getPlace().getLongitude()).isEqualTo(127.123456);
    }
}
