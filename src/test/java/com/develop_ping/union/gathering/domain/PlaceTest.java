package com.develop_ping.union.gathering.domain;

import com.develop_ping.union.gathering.exception.GatheringValidationException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("장소 도메인 테스트")
class PlaceTest {

    @DisplayName("위도는 33.0도보다 크고 38.6도보다 작아야 한다.")
    @Test
    void validateLatitudeRange() {
        // given
        Double validSmallLatitude = 33.0;
        Double validLargeLatitude = 38.6;
        Double tooSmallLatitude = 32.9;
        Double tooLargeLatitude = 38.7;
        Double validLongitude = 126.9;

        // when & then - 위도 값이 작을 때
        GatheringValidationException smallLatitudeException = assertThrows(
            GatheringValidationException.class, () -> new Place("서울", tooSmallLatitude, validLongitude)
        );
        assertThat(smallLatitudeException.getMessage()).isEqualTo("위도는 33.0 ~ 38.6 사이여야 합니다. (한국 내)");

        // when & then - 위도 값이 클 때
        GatheringValidationException largeLatitudeException = assertThrows(
            GatheringValidationException.class, () -> new Place("서울", tooLargeLatitude, validLongitude)
        );
        assertThat(largeLatitudeException.getMessage()).isEqualTo("위도는 33.0 ~ 38.6 사이여야 합니다. (한국 내)");

        // when & then - 유효한 위도 값
        Place smallPlace = assertDoesNotThrow(() -> new Place("서울", validSmallLatitude, validLongitude));
        assertThat(smallPlace.getLatitude()).isEqualTo(validSmallLatitude);
        // when & then - 유효한 위도 값
        Place largePlace = assertDoesNotThrow(() -> new Place("서울", validLargeLatitude, validLongitude));
        assertThat(largePlace.getLatitude()).isEqualTo(validLargeLatitude);

    }

    @DisplayName("경도는 124.6도보다 크고 131.9도보다 작아야 한다.")
    @Test
    void validateLongitudeRange() {
        // given
        Double validSmallLongitude = 124.6;
        Double validLargeLongitude = 131.9;
        Double tooSmallLongitude = 124.5;
        Double tooLargeLongitude = 132.0;
        Double validLatitude = 37.0;

        // when & then - 경도 값이 작을 때
        GatheringValidationException smallLongitudeException = assertThrows(
            GatheringValidationException.class, () -> new Place("서울", validLatitude, tooSmallLongitude)
        );
        assertThat(smallLongitudeException.getMessage()).isEqualTo("경도는 124.6 ~ 131.9 사이여야 합니다. (한국 내)");

        // when & then - 경도 값이 클 때
        GatheringValidationException largeLongitudeException = assertThrows(
            GatheringValidationException.class, () -> new Place("서울", validLatitude, tooLargeLongitude)
        );
        assertThat(largeLongitudeException.getMessage()).isEqualTo("경도는 124.6 ~ 131.9 사이여야 합니다. (한국 내)");

        // when & then - 유효한 small 경도 값
        Place smallPlace = assertDoesNotThrow(() -> new Place("서울", validLatitude, validSmallLongitude));
        assertThat(smallPlace.getLongitude()).isEqualTo(validSmallLongitude);

        // when & then - 유효한 large 경도 값
        Place largePlace = assertDoesNotThrow(() -> new Place("서울", validLatitude, validLargeLongitude));
        assertThat(largePlace.getLongitude()).isEqualTo(validLargeLongitude);
    }
}
