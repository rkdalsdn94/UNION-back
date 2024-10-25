package com.develop_ping.union.photo.presentation.dto;

import com.develop_ping.union.photo.domain.dto.PhotoCommand;
import com.develop_ping.union.photo.domain.entity.TargetType;
import com.develop_ping.union.user.domain.entity.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PhotoRequest {
    @NotNull
    private Long targetId;

    @NotBlank
    private TargetType targetType;

    @NotEmpty
    private List<String> urls;

    @Builder
    public PhotoRequest(Long targetId, TargetType targetType, List<String> urls) {
        this.targetId = targetId;
        this.targetType = targetType;
        this.urls = urls;
    }

    public PhotoCommand toCommand(User user) {
        return PhotoCommand.builder()
                .user(user)
                .targetId(this.targetId)
                .targetType(this.targetType)
                .urls(this.urls)
                .build();
    }
}
