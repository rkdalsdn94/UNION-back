package com.develop_ping.union.notification.domain.service;

import com.develop_ping.union.comment.domain.CommentManager;
import com.develop_ping.union.gathering.domain.GatheringManager;
import com.develop_ping.union.notification.domain.NotificationManager;
import com.develop_ping.union.notification.domain.dto.NotificationCommand;
import com.develop_ping.union.notification.domain.dto.NotificationInfo;
import com.develop_ping.union.notification.domain.dto.NotificationListInfo;
import com.develop_ping.union.notification.domain.entity.Notification;
import com.develop_ping.union.notification.infra.dto.NotificationReadForService;
import com.develop_ping.union.party.domain.PartyManager;
import com.develop_ping.union.party.domain.entity.PartyRole;
import com.develop_ping.union.post.domain.PostManager;
import com.develop_ping.union.user.domain.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService{
    private final NotificationManager notificationManager;
    private final CommentManager commentManager;
    private final PostManager postManager;
    private final PartyManager partyManager;
    @Override
    public NotificationInfo createNotificationForPost(NotificationCommand command) {
        log.info("[ CALL: NotificationService.createNotificationForPost() ] POST-type id: {}", command.getTypeId());

        // extract post owner
        User creator = postManager.findById(command.getTypeId()).getUser();

        // extract comment owner
        User attendee = commentManager.findById(command.getCommentId()).getUser();

        Notification notification = notificationManager.save(Notification.of(command.getType(),
                command.getTypeId(), command.getCommentId(), false, creator, attendee));

        return NotificationInfo.of(notification);
    }

    @Override
    public NotificationInfo createNotificationForComment(NotificationCommand command) {
        log.info("[ CALL: NotificationService.createNotificationForComment() ] COMMENT-type id: {}", command.getTypeId());

        // extract comment owner
        User creator = commentManager.findById(command.getTypeId()).getUser();

        // extract re-comment owner
        User attendee = commentManager.findById(command.getCommentId()).getUser();

        Notification notification = notificationManager.save(Notification.of(command.getType(),
                command.getTypeId(), command.getCommentId(), false, creator, attendee));

        return NotificationInfo.of(notification);
    }

    @Override
    public NotificationInfo createNotificationForGathering(NotificationCommand command) {
        log.info("[ CALL: NotificationService.createNotificationForGathering() ] GATHERING-type id: {}", command.getTypeId());

        // extract gathering owner
        User creator = partyManager.findOwnerByGatheringIdAndRole(command.getTypeId(), PartyRole.OWNER).orElseThrow().getUser();

        // extract user
        User attendee = command.getUser();

        Notification notification = notificationManager.save(Notification.of(command.getType(),
                command.getTypeId(), 0L, false, creator, attendee));

        return NotificationInfo.of(notification);
    }

    @Override
    public NotificationListInfo readNotification(NotificationCommand command) {
        log.info("[ CALL: NotificationService.readNotification() ] user id: {}", command.getUser().getId());

        List<NotificationReadForService> notificationForServices = notificationManager.findAllOrderByDate(command.getPage(), command.getSize(), command.getUser());
        return NotificationListInfo.of(notificationForServices);
    }

    @Override
    @Transactional
    public NotificationInfo updateNotification(NotificationCommand command) {
        log.info("[ CALL: NotificationService.updateNotification() ] user id: {}", command.getUser().getId());

        if (!notificationManager.isExistNotification(command.getId()))
            throw new IllegalArgumentException("id와 일치하는 notification 없음");

        Notification notification = notificationManager.updateIsRead(command.getId(), command.getUser());

        return NotificationInfo.of(notification);
    }
}
