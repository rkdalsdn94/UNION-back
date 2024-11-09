package com.develop_ping.union.notification.infra;

import com.develop_ping.union.notification.domain.entity.Notification;
import com.develop_ping.union.notification.infra.dto.NotificationReadForService;
import com.develop_ping.union.user.domain.entity.User;

import java.util.List;

public interface NotificationRepositoryCustom {
    List<NotificationReadForService> findAllOrderById(Long page, Long size, User user);
    void updateAll(Long page, Long size, User user);
}
