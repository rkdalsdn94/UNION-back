package com.develop_ping.union.notification.presentation.dto.response;

import com.develop_ping.union.notification.domain.dto.NotificationInfo;
import com.develop_ping.union.notification.domain.dto.NotificationListInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class NotificationReadForResponseList {
    private List<NotificationReadForResponse> notifications;

    public static NotificationReadForResponseList from(NotificationListInfo info) {
        List<NotificationReadForResponse> notifications = new ArrayList<>();

        for(NotificationInfo notify : info.getNotifications()){
            notifications.add(NotificationReadForResponse.builder()
                            .id(notify.getId())
                            .type(notify.getType())
                            .typeId(notify.getTypeId())
                            .nickname(notify.getNickname())
                            .title(notify.getTitle())
                            .content(notify.getContent())
                            .createdAt(notify.getCreatedAt())
                            .isRead(notify.getIsRead())
                    .build());
        }

        return NotificationReadForResponseList.builder()
                .notifications(notifications)
                .build();
    }
}
