package com.develop_ping.union.photo.domain.dto;

import com.develop_ping.union.photo.domain.entity.TargetType;
import com.develop_ping.union.user.domain.entity.User;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class PhotoCommand {
    private final User user;
    private final Long targetId;
    private final TargetType targetType;
    private final List<String> urls;

    @Builder
    public PhotoCommand(User user, Long targetId, TargetType targetType, List<String> urls) {
        this.user = user;
        this.targetId = targetId;
        this.targetType = targetType;
        this.urls = urls;
    }


}
