package com.develop_ping.union.notification.presentation.dto.request;

import com.develop_ping.union.notification.domain.dto.NotificationCommand;
import com.develop_ping.union.user.domain.entity.User;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class NotificationUpdateIsReadRequest {
    private Long id;
    public NotificationCommand toCommand(User user){
        return NotificationCommand.builder()
                .user(user)
                .id(this.id)
                .build();
    }
}
