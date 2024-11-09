package com.develop_ping.union.notification.infra.dto;

import com.develop_ping.union.notification.domain.NotiType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class NotificationReadForService {
    private Long id;
    private NotiType type;
    private String nickname;
    private String title;
    private String content;
    private ZonedDateTime createdAt;
    private Boolean isRead;
}
