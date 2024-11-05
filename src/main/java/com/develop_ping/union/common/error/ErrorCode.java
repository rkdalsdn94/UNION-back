package com.develop_ping.union.common.error;

import lombok.Getter;

@Getter
public enum ErrorCode {
    // 400 Bad Request
    INPUT_VALUE_INVALID("INPUT_VALUE_INVALID", "데이터 형식이 맞지 않습니다.", 400),
    UNSUPPORTED_FILE_FORMAT("UNSUPPORTED_FILE_FORMAT", "지원하지 않는 파일 형식입니다.", 400),
    INVALID_S3_URL("INVALID_S3_URL", "등록되지 않은 이미지 URL이 전달되었습니다.", 400),
    COMMENTER_MISMATCH("COMMENTER_MISMATCH", "댓글 작성자가 일치하지 않습니다.", 400),

    // 401 Unauthorized
    INVALID_TOKEN("INVALID_TOKEN", "유효하지 않은 JWT 토큰입니다.", 401),
    OAUTH_NOT_PREPARED("OAUTH_NOT_PREPARED", "Oauth 등록이 수행되지 않았습니다.", 401),

    // 403 Forbidden
    POST_PERMISSION_DENIED("POST_PERMISSION_DENIED", "해당 게시글에 대한 권한이 없습니다.", 403),
    COMMENT_PERMISSION_DENIED("COMMENT_PERMISSION_DENIED", "해당 댓글에 대한 권한이 없습니다.", 403),
    GATHERING_PERMISSION_DENIED("GATHERING_PERMISSION_DENIED", "해당 모임에 대한 권힌이 없습니다.", 403),
    USER_BLOCKED("USER_BLOCKED", "차단된 유저의 정보입니다.", 403),
    OWNER_CANNOT_EXIT("OWNER_CANNOT_EXIT", "주최자는 모임에서 나갈 수 없습니다.", 403),

    // 404 Not Found
    USER_NOT_FOUND("USER_NOT_FOUND", "해당 유저를 찾을 수 없습니다.", 404),
    POST_NOT_FOUND("POST_NOT_FOUND", "해당 게시글을 찾을 수 없습니다.", 404),
    GATHERING_NOT_FOUND("GATHERING_NOT_FOUND", "해당 모임을 찾을 수 없습니다.", 404),
    COMMENT_NOT_FOUND("COMMENT_NOT_FOUND", "해당 댓글을 찾을 수 없습니다.", 404),
    BLOCK_RELATIONSHIP_NOT_FOUND("BLOCK_RELATIONSHIP_NOT_FOUND", "차단된 유저가 아닙니다.", 404),
    PARTICIPATION_NOT_FOUND("PARTICIPATION_NOT_FOUND", "참여하지 않은 모임입니다.", 404),
    CHATROOM_NOT_FOUND("CHATROOM_NOT_FOUND", "해당 채팅방을 찾을 수 없습니다.", 404),

    // 409 Conflict
    DUPLICATE_NICKNAME("DUPLICATE_NICKNAME", "이미 존재하는 닉네임입니다.", 409),
    ALREADY_JOINED("ALREADY_JOINED", "이미 가입되었습니다.", 409),
    PARTICIPANT_LIMIT_EXCEEDED("PARTICIPANT_LIMIT_EXCEEDED", "정원이 가득 찼습니다.", 409),
    RECRUITMENT_ALREADY_COMPLETED("RECRUITMENT_ALREADY_COMPLETED", "모집이 이미 완료되었습니다.", 409),

    // 422 Unprocessable Entity
    IMAGE_UPLOAD_FAILED("IMAGE_UPLOAD_FAILED", "이미지 업로드에 실패하였습니다.", 422),
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
