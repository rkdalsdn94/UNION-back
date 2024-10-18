package com.develop_ping.union.common.error;

import com.develop_ping.union.common.exception.*;
import com.develop_ping.union.common.exception.gathering.*;
import com.develop_ping.union.image.exception.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.List;

import static com.develop_ping.union.common.error.ErrorBuildFactory.*;

@Slf4j
@RestControllerAdvice
public class ErrorHandlingController {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error("잘못된 요청 값이 전달되었습니다.");
        List<ErrorResponse.FieldError> fieldErrors = getFieldErrors(e.getBindingResult());
        return buildFieldErrors(ErrorCode.INPUT_VALUE_INVALID, fieldErrors);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ErrorResponse handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        log.error("타입이 맞지 않는 요청 값입니다.");
        ErrorResponse.FieldError fieldError = new ErrorResponse.FieldError(e.getPropertyName(), (String) e.getValue(), e.getMessage());
        return buildFieldErrors(ErrorCode.INPUT_VALUE_INVALID, List.of(fieldError));
    }

    @ExceptionHandler(InvalidTokenException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    protected ErrorResponse handleInvalidTokenException(InvalidTokenException e) {
        log.error("유효하지 않은 JWT 토큰입니다.");
        return buildError(ErrorCode.INVALID_TOKEN);
    }

    @ExceptionHandler(InvalidRefreshTokenException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorResponse handleInvalidRefreshTokenException(InvalidRefreshTokenException e) {
        log.warn("유효하지 않은 리프레시 토큰입니다.");
        return buildError(ErrorCode.INVALID_REFRESH_TOKEN);
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected ErrorResponse handleUserNotFoundException(UserNotFoundException e) {
        log.error("해당 유저를 찾을 수 없습니다.");
        log.error("user id: {}", e.getUserId());
        return buildError(ErrorCode.USER_NOT_FOUND);
    }

    @ExceptionHandler(PostNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected ErrorResponse handlePostNotFoundException(PostNotFoundException e) {
        log.error("해당 게시글을 찾을 수 없습니다.");
        log.error("post id: {}", e.getPostId());
        return buildError(ErrorCode.POST_NOT_FOUND);
    }

    @ExceptionHandler(GatheringNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected ErrorResponse handleGatheringNotFoundException(GatheringNotFoundException e) {
        log.error("해당 모임을 찾을 수 없습니다.");
        log.error("gathering id: {}", e.getGatheringId());
        return buildError(ErrorCode.GATHERING_NOT_FOUND);
    }

    @ExceptionHandler(DuplicateNicknameException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    protected ErrorResponse handleDuplicateNicknameException(DuplicateNicknameException e) {
        log.error("이미 존재하는 닉네임입니다.");
        log.error("nickname: {}", e.getNickname());
        return buildError(ErrorCode.DUPLICATE_NICKNAME);
    }

    @ExceptionHandler(UnsupportedFileFormatException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ErrorResponse handleUnsupportedFileFormatException(UnsupportedFileFormatException e) {
        log.error("지원하지 않는 파일 형식입니다.");
        log.error("extension: {}", e.getExtension());
        return buildError(ErrorCode.UNSUPPORTED_FILE_FORMAT);
    }

    @ExceptionHandler(ImageUploadFailedException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    protected ErrorResponse handleImageUploadFailedException(ImageUploadFailedException e) {
        log.error("이미지 업로드에 실패하였습니다.");
        log.error("file name: {}", e.getFileName());
        return buildError(ErrorCode.IMAGE_UPLOAD_FAILED);
    }
}
