package com.develop_ping.union.notification.infra;

import com.develop_ping.union.notification.domain.NotificationManager;
import com.develop_ping.union.notification.domain.entity.Notification;
import com.develop_ping.union.notification.infra.dto.NotificationReadForService;
import com.develop_ping.union.user.domain.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class NotificationManagerImpl implements NotificationManager {
    private final NotificationRepository notificationRepository;

    public Notification save(Notification notification){
        return notificationRepository.save(notification);
    }

    @Override
    public List<NotificationReadForService> findAllOrderByDate(Long page, Long size, User user) {
        log.info("[ CALL: NotificationManager.findAllOrderByDate() ] user id: {}", user.getId());
        return notificationRepository.findAllOrderById(page, size, user);
    }

    @Override
    public Notification updateIsRead(Long id, User user) {
        log.info("[ CALL: NotificationManager.updateIsRead() ] user id: {}", user.getId());
        return notificationRepository.updateIsRead(id, user);
    }

    @Override
    public Boolean isExistNotification(Long id) {
        log.info("[ CALL: NotificationManager.isExistNotification() ] notification id: {}", id);
        return notificationRepository.existsById(id);
    }
}

