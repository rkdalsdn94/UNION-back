package com.develop_ping.union.notification.presentation.dto.response;

import com.develop_ping.union.notification.domain.dto.NotificationInfo;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NotificationUpdateIsReadResponse {
    private Long id;
    private Boolean isRead;

    public static NotificationUpdateIsReadResponse from(NotificationInfo notificationInfo){
        return NotificationUpdateIsReadResponse.builder()
                .id(notificationInfo.getId())
                .isRead(notificationInfo.getIsRead())
                .build();
    }
}
