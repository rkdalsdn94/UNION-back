package com.develop_ping.union.gathering.infra;

import com.develop_ping.union.IntegrationTestSupport;
import com.develop_ping.union.gathering.domain.entity.Gathering;
import com.develop_ping.union.gathering.domain.entity.Place;
import com.develop_ping.union.gathering.exception.GatheringNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;

import static org.assertj.core.api.Assertions.*;

@DisplayName("모임 Repository 테스트")
@Transactional
class GatheringRepositoryTest extends IntegrationTestSupport {

    @Autowired
    private GatheringRepository gatheringRepository;

    @DisplayName("모임을 저장하면 저장된 모임의 세부 정보가 반환된다")
    @Test
    void givenGathering_whenSaveGathering_thenGatheringIsSavedSuccessfully() {
        // given
        Gathering gathering = createDefaultGathering();

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

    @DisplayName("저장된 모임을 조회하면 모임의 세부 정보가 반환된다")
    @Test
    void givenSavedGathering_whenFindGatheringById_thenCorrectDetailsAreReturned() {
        // given
        Gathering gathering = createDefaultGathering();
        gatheringRepository.save(gathering);

        // when
        Gathering findGathering = gatheringRepository.findById(gathering.getId()).orElse(null);

        // then
        assertThat(findGathering).isNotNull();
        assertThat(findGathering.getId()).isNotNull();
        assertThat(findGathering.getTitle()).isEqualTo("모임 제목");
        assertThat(findGathering.getContent()).isEqualTo("모임 내용");
        assertThat(findGathering.getMaxMember()).isEqualTo(5);
        assertThat(findGathering.getCurrentMember()).isEqualTo(1);
        assertThat(findGathering.getGatheringDateTime()).isNotNull();
        assertThat(findGathering.getPlace()).isNotNull();
        assertThat(findGathering.getPlace().getAddress()).isEqualTo("장소");
        assertThat(findGathering.getPlace().getLatitude()).isEqualTo(37.123456);
        assertThat(findGathering.getPlace().getLongitude()).isEqualTo(127.123456);
    }

    @DisplayName("존재하지 않는 모임을 조회하면 예외가 발생한다")
    @Test
    void givenNonExistentGatheringId_whenFindGatheringById_thenThrowsGatheringNotFoundException() {
        // given
        Long nonExistentGatheringId = 999L;

        // when
        Throwable throwable = catchThrowable(() -> {
            gatheringRepository.findById(nonExistentGatheringId)
                               .orElseThrow(() -> new GatheringNotFoundException(nonExistentGatheringId));
        });

        // then
        assertThat(throwable).isInstanceOf(GatheringNotFoundException.class)
                             .hasMessageContaining("999");
    }

    private Gathering createDefaultGathering() {
        return Gathering.builder()
                        .title("모임 제목")
                        .content("모임 내용")
                        .maxMember(5)
                        .gatheringDateTime(ZonedDateTime.now().plusHours(2))
                        .place(new Place("장소", 37.123456, 127.123456))
                        .build();
    }
}
