package com.develop_ping.union.notification.presentation.dto.response;

import com.develop_ping.union.notification.domain.NotiType;
import com.develop_ping.union.notification.domain.dto.NotificationInfo;
import com.develop_ping.union.user.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class NotificationCreationForPostResponse {
    private Long id;
    private ZonedDateTime createdAt;
    private Boolean isRead;
    public static NotificationCreationForPostResponse from(NotificationInfo info){
        return NotificationCreationForPostResponse.builder()
                .id(info.getId())
                .createdAt(info.getCreatedAt())
                .isRead(info.getIsRead())
                .build();
    }
}
