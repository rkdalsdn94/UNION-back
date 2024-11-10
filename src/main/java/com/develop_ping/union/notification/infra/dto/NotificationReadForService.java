package com.develop_ping.union.notification.infra.dto;

import com.develop_ping.union.notification.domain.NotiType;
import com.develop_ping.union.post.domain.entity.PostType;
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
    private Long typeId;
    private String nickname;
    private String title;
    private String content;
    private ZonedDateTime createdAt;
    private Boolean isRead;
    private PostType postType;
}
