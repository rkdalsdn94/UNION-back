package com.develop_ping.union.learningtest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import static org.assertj.core.api.Assertions.assertThat;

public class ClockTest {

    @DisplayName("Clock을 이용해서 현재 시간을 가져올 수 있다.")
    @Test
    void clock() {
        // given
        Clock clock = Clock.systemDefaultZone();

        // when
        ZonedDateTime dateTime1 = ZonedDateTime.now(clock);
        ZonedDateTime dateTime2 = ZonedDateTime.now(clock);

        // then
        assertThat(dateTime2.isAfter(dateTime1));
    }

    @DisplayName("Clock의 fixed를 이용하면 시간을 고정할 수 있다.")
    @Test
    void Clock_Fixed_Test() {
        // given
        Clock clock = Clock.fixed(Instant.now(), ZoneId.systemDefault());

        // when
        ZonedDateTime dateTime1 = ZonedDateTime.now(clock);
        ZonedDateTime dateTime2 = ZonedDateTime.now(clock);
        ZonedDateTime dateTime3 = ZonedDateTime.now(clock).plusHours(1);

        // then
        assertThat(dateTime1).isEqualTo(dateTime2);
        assertThat(dateTime3).isEqualTo(dateTime1.plusHours(1));
    }
}
