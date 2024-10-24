package com.develop_ping.union.photo.presentation.dto;

import com.develop_ping.union.photo.domain.dto.PhotoCommand;
import com.develop_ping.union.photo.domain.entity.TargetType;
import com.develop_ping.union.photo.exception.InvalidTargetTypeException;
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
    private String targetType;

    @NotEmpty
    private List<String> urls;

    @Builder
    public PhotoRequest(Long targetId, String targetType, List<String> urls) {
        this.targetId = targetId;
        this.targetType = targetType;
        this.urls = urls;
    }

    public PhotoCommand toCommand() {
        TargetType targetType = convertToTargetType(this.targetType);

        return PhotoCommand.builder()
                .targetId(this.targetId)
                .targetType(targetType)
                .urls(this.urls)
                .build();
    }

    private TargetType convertToTargetType(String targetType) {
        try {
            return TargetType.valueOf(targetType.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidTargetTypeException(targetType);
        }
    }
}
