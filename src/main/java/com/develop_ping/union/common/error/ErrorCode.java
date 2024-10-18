package com.develop_ping.union.common.error;

import lombok.Getter;

@Getter
public enum ErrorCode {
    // 400 Bad Request
    INPUT_VALUE_INVALID("INPUT_VALUE_INVALID", "데이터 형식이 맞지 않습니다.", 400),
    UNSUPPORTED_FILE_FORMAT("UNSUPPORTED_FILE_FORMAT", "지원하지 않는 파일 형식입니다.", 400),

    // 401 Unauthorized
    INVALID_TOKEN("INVALID_TOKEN", "유효하지 않은 JWT 토큰입니다.", 401),
    INVALID_REFRESH_TOKEN("INVALID_REFRESH_TOKEN", "유효하지 않은 리프레시 토큰입니다.", 401),

    // 404 Not Found
    USER_NOT_FOUND("USER_NOT_FOUND", "해당 유저를 찾을 수 없습니다.", 404),
    POST_NOT_FOUND("POST_NOT_FOUND", "해당 게시글을 찾을 수 없습니다.", 404),
    GATHERING_NOT_FOUND("GATHERING_NOT_FOUND", "해당 모임을 찾을 수 없습니다.", 404),

    // 409 Conflict
    DUPLICATE_NICKNAME("DUPLICATE_NICKNAME", "이미 존재하는 닉네임입니다.", 409),
    ;

    private final String code;
    private final String message;
    private final Integer status;

    ErrorCode(String code, String message, Integer status) {
        this.code = code;
        this.message = message;
        this.status = status;
    }
}
