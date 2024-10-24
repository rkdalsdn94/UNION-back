package com.develop_ping.union.photo.domain.dto;

import com.develop_ping.union.photo.domain.entity.TargetType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PhotoCommand {
    private Long targetId;
    private TargetType targetType;
    private List<String> urls;

    @Builder
    public PhotoCommand(Long targetId, TargetType targetType, List<String> urls) {
        this.targetId = targetId;
        this.targetType = targetType;
        this.urls = urls;
    }


}
