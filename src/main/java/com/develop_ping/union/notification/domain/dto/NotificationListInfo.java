package com.develop_ping.union.notification.domain.dto;

import com.develop_ping.union.notification.domain.NotiType;
import com.develop_ping.union.notification.domain.entity.Notification;
import com.develop_ping.union.notification.infra.dto.NotificationReadForService;
import com.develop_ping.union.user.domain.entity.User;
import lombok.Builder;
import lombok.Data;
import org.apache.http.impl.entity.LaxContentLengthStrategy;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class NotificationListInfo {
    private List<NotificationInfo> notifications;

    public static NotificationListInfo of(List<NotificationReadForService> notifications) {
        List<NotificationInfo> result = new ArrayList<>();

        for (NotificationReadForService notification : notifications) {
            result.add(NotificationInfo.builder()
                    .id(notification.getId())
                    .type(notification.getType())
                    .nickname(notification.getNickname())
                    .title(notification.getTitle())
                    .content(notification.getContent())
                    .createdAt(notification.getCreatedAt())
                    .isRead(notification.getIsRead())
                    .build());
        }

        return NotificationListInfo.builder().notifications(result).build();
    }


}
