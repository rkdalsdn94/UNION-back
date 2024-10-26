package com.develop_ping.union.gathering.domain.entity;

import com.develop_ping.union.gathering.exception.GatheringValidationException;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Place {

    @Column(name = "address")
    private String address;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "longitude")
    private Double longitude;

    @Builder
    public Place(String address, Double latitude, Double longitude) {
        checkLatitudeAndLongitude(latitude, longitude);

        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    private void checkLatitudeAndLongitude(Double latitude, Double longitude) {
        if (isNotNull(latitude)) {
            validateLatitude(latitude);
        }
        if (isNotNull(longitude)) {
            validateLongitude(longitude);
        }
    }

    private boolean isNotNull(Double value) {
        return value != null;
    }

    private void validateLatitude(Double latitude) {
        if (latitude < 33.0 || latitude > 38.6) {
            throw new GatheringValidationException("위도는 33.0 ~ 38.6 사이여야 합니다. (한국 내)");
        }
    }

    private void validateLongitude(Double longitude) {
        if (longitude < 124.6 || longitude > 131.9) {
            throw new GatheringValidationException("경도는 124.6 ~ 131.9 사이여야 합니다. (한국 내)");
        }
    }
}
