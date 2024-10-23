package com.develop_ping.union.gathering.domain;

import com.develop_ping.union.gathering.exception.GatheringValidationException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("모임 생성시 입력값 검증 테스트")
class GatheringCreateValidationTest {

    @DisplayName("모임 인원은 최소 2명 이상 최대 15명까지 가능하다.")
    @Test
    void createGatheringWithInvalidMemberCount() {
        // given
        ZonedDateTime requestTime = ZonedDateTime.now().plusHours(2);

        // when - 인원이 너무 적을 때 (1명)
        Throwable tooFewMembersThrowable = catchThrowable(() -> {
            Gathering.builder()
                     .title("Valid Title")
                     .content("Valid Content")
                     .gatheringDateTime(requestTime)
                     .maxMember(1)
                     .build();
        });

        // then
        assertThat(tooFewMembersThrowable).isInstanceOf(GatheringValidationException.class)
                                          .hasMessage("모임은 최소 2명 이상 최대 15명까지 가능합니다.");

        // when - 인원이 경계값일 때 (2명, 15명)
        assertDoesNotThrow(() -> {
            Gathering.builder()
                     .title("Valid Title")
                     .content("Valid Content")
                     .gatheringDateTime(requestTime)
                     .maxMember(2)
                     .build();
        });

        assertDoesNotThrow(() -> {
            Gathering.builder()
                     .title("Valid Title")
                     .content("Valid Content")
                     .gatheringDateTime(requestTime)
                     .maxMember(15)
                     .build();
        });

        // when - 인원이 너무 많을 때 (16명)
        Throwable tooManyMembersThrowable = catchThrowable(() -> {
            Gathering.builder()
                     .title("Valid Title")
                     .content("Valid Content")
                     .gatheringDateTime(requestTime)
                     .maxMember(16)
                     .build();
        });

        // then
        assertThat(tooManyMembersThrowable).isInstanceOf(GatheringValidationException.class)
                                           .hasMessage("모임은 최소 2명 이상 최대 15명까지 가능합니다.");
    }

    @DisplayName("모임 일시는 현재 시간에서 30분 이후로 설정해야 한다.")
    @Test
    void createGatheringWithInvalidDateTime() {
        // given
        Clock clock = Clock.fixed(Instant.now(), ZoneId.systemDefault());

        ZonedDateTime now = ZonedDateTime.now(clock);
        ZonedDateTime pastTime = now.plusMinutes(29); // 30분 이전
        ZonedDateTime validTime = now.plusMinutes(30); // 30분 (경계값)

        // when - 30분 이전일 때
        Throwable throwable = catchThrowable(() -> {
            Gathering.builder()
                     .title("Valid Title")
                     .content("Valid Content")
                     .gatheringDateTime(pastTime)
                     .maxMember(5)
                     .build();
        });
        // then
        assertThat(throwable).isInstanceOf(GatheringValidationException.class)
                             .hasMessage("모임 일시는 현재 시간에서 30분 이후로 설정해주세요.");

        // when - 경계값 (30분)일 때
        assertDoesNotThrow(() -> {
            Gathering.builder()
                     .title("Valid Title")
                     .content("Valid Content")
                     .gatheringDateTime(validTime)
                     .maxMember(5)
                     .build();
        });
    }

    @DisplayName("모임 생성 시 currentMember가 null이면 1로 초기화 된다.")
    @Test
    void initializeCurrentMemberWhenNull() {
        // given
        ZonedDateTime validTime = ZonedDateTime.now().plusHours(1);
        Gathering gathering = Gathering.builder()
                                       .title("제목제목")
                                       .content("내용내용")
                                       .gatheringDateTime(validTime)
                                       .maxMember(5)
                                       .currentMember(null)
                                       .build();

        // when & then
        assertThat(gathering.getCurrentMember()).isEqualTo(1);
    }
}
