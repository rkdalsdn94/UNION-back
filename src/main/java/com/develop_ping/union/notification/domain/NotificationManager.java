package com.develop_ping.union.notification.domain;

import com.develop_ping.union.notification.domain.dto.NotificationInfo;
import com.develop_ping.union.notification.domain.entity.Notification;
import com.develop_ping.union.notification.infra.dto.NotificationReadForService;
import com.develop_ping.union.user.domain.entity.User;

import java.util.List;

public interface NotificationManager {
    Notification save(Notification notification);
    List<NotificationReadForService> findAllOrderByDate(Long page, Long size, User user);
    void updateAll(Long page, Long size, User user);
}
