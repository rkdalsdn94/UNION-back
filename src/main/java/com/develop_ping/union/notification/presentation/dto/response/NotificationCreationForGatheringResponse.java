package com.develop_ping.union.notification.presentation.dto.response;

import com.develop_ping.union.notification.domain.NotiType;
import com.develop_ping.union.notification.domain.dto.NotificationInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class NotificationCreationForGatheringResponse {
    private Long id;
    private ZonedDateTime createdAt;
    private Boolean isRead;
    public static NotificationCreationForGatheringResponse from(NotificationInfo info){
        return NotificationCreationForGatheringResponse.builder()
                .id(info.getId())
                .createdAt(info.getCreatedAt())
                .isRead(info.getIsRead())
                .build();
    }
}
