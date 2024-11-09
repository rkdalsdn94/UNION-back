package com.develop_ping.union.notification.domain.dto;

import com.develop_ping.union.notification.domain.NotiType;
import com.develop_ping.union.notification.domain.entity.Notification;
import com.develop_ping.union.user.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.aspectj.weaver.ast.Not;

import java.time.ZonedDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class NotificationInfo {
    private Long id;
    private NotiType type;
    private Long typeId;
    private Long creatorTypeId;
    private Long attendeeTypeId;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;
    private Boolean isRead;
    private User creator;
    private User attendee;
    private String nickname;
    private String title;
    private String content;
    private List<Notification> notifications;

    public static NotificationInfo of(Notification notification){
        return NotificationInfo.builder()
                .id(notification.getId())
                .creator(notification.getCreator())
                .attendee(notification.getAttendee())
                .type(notification.getType())
                .creatorTypeId(notification.getCreatorTypeId())
                .attendeeTypeId(notification.getAttendeeTypeId())
                .createdAt(ZonedDateTime.now())
                .updatedAt(ZonedDateTime.now())
                .isRead(notification.getIsRead())
                .build();
    }

    public static NotificationInfo readOf(List<Notification> notifications){
        return NotificationInfo.builder()
                .notifications(notifications)
                .build();
    }
}
