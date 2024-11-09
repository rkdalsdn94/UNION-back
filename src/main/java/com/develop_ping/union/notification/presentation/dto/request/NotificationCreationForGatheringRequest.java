package com.develop_ping.union.notification.presentation.dto.request;

import com.develop_ping.union.notification.domain.NotiType;
import com.develop_ping.union.notification.domain.dto.NotificationCommand;
import com.develop_ping.union.user.domain.entity.User;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class NotificationCreationForGatheringRequest {
    @NotNull
    private Long typeId;

    public NotificationCommand toCommand(User user) {
        return NotificationCommand.builder()
                .type(NotiType.GATHERING)
                .typeId(this.typeId)
                .commentId(0L)
                .user(user)
                .build();
    }
}
