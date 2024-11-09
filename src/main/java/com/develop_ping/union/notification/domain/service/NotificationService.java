package com.develop_ping.union.notification.domain.service;

import com.develop_ping.union.notification.domain.NotificationManager;
import com.develop_ping.union.notification.domain.dto.NotificationCommand;
import com.develop_ping.union.notification.domain.dto.NotificationInfo;
import com.develop_ping.union.notification.domain.dto.NotificationListInfo;

public interface NotificationService {
    NotificationInfo createNotificationForPost(NotificationCommand command);
    NotificationInfo createNotificationForComment(NotificationCommand command);
    NotificationInfo createNotificationForGathering(NotificationCommand command);
    NotificationListInfo readNotification(NotificationCommand command);
    void updateNotification(NotificationCommand command);
}
