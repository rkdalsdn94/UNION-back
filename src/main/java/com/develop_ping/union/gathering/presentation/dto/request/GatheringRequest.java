package com.develop_ping.union.gathering.presentation.dto.request;

import com.develop_ping.union.gathering.domain.dto.request.GatheringCommand;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GatheringRequest {

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    @NotNull
    private Integer maxMember;

    private Integer currentMember;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private ZonedDateTime gatheringDateTime;

    private String address;
    private Double latitude;
    private Double longitude;
    private String eupMyeonDong;
    private String thumbnail;

    @Builder
    private GatheringRequest(
        String title,
        String content,
        Integer maxMember,
        Integer currentMember,
        ZonedDateTime gatheringDateTime,
        String address,
        Double latitude,
        Double longitude,
        String eupMyeonDong,
        String thumbnail
    ) {
        this.title = title;
        this.content = content;
        this.maxMember = maxMember;
        this.currentMember = currentMember;
        this.gatheringDateTime = gatheringDateTime;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.eupMyeonDong = eupMyeonDong;
        this.thumbnail = thumbnail;
    }

    public GatheringCommand toCommand() {
        return GatheringCommand.builder()
            .title(title)
            .content(content)
            .maxMember(maxMember)
            .currentMember(currentMember)
            .gatheringDateTime(gatheringDateTime)
            .address(address)
            .latitude(latitude)
            .longitude(longitude)
            .eupMyeonDong(eupMyeonDong)
            .thumbnail(thumbnail)
            .build();
    }

    @Override
    public String toString() {
        return "'\n'GatheringRequest{" +
            "address='" + address + '\'' +
            ", title='" + title + '\'' +
            ", content='" + content + '\'' +
            ", maxMember=" + maxMember +
            ", currentMember=" + currentMember +
            ", gatheringDateTime=" + gatheringDateTime +
            ", latitude=" + latitude +
            ", longitude=" + longitude +
            ", eupMyeonDong=" + eupMyeonDong +
            ", thumbnail='" + thumbnail + '\'' +
            '}';
    }
}
