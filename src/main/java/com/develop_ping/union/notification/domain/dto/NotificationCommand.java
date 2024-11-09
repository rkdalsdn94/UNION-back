package com.develop_ping.union.notification.domain.dto;

import com.develop_ping.union.notification.domain.NotiType;
import com.develop_ping.union.notification.presentation.dto.request.NotificationCreationForPostRequest;
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
public class NotificationCommand {
    private NotiType type;
    private Long typeId;
    private Long commentId;
    private Long page;
    private Long size;
    private User user;

    public static NotificationCommand readOf(Long page, Long size, User user){
        return NotificationCommand.builder()
                .page(page)
                .size(size)
                .user(user)
                .build();
    }

}
