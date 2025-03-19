package com.develop_ping.union.common.error;


import static com.develop_ping.union.common.error.ErrorBuildFactory.buildError;
import static com.develop_ping.union.common.error.ErrorBuildFactory.buildFieldErrors;
import static com.develop_ping.union.common.error.ErrorBuildFactory.getFieldErrors;

import com.develop_ping.union.gathering.exception.GatheringPermissionDeniedException;
import com.develop_ping.union.gathering.exception.GatheringValidationException;
import com.develop_ping.union.gathering.exception.NoMatchingResultsException;
import com.develop_ping.union.gathering.exception.OwnerCannotExitException;
import com.develop_ping.union.gathering.exception.ParticipantLimitExceededException;
import com.develop_ping.union.gathering.exception.RecruitmentAlreadyCompletedException;
import com.develop_ping.union.party.exception.AlreadyJoinedException;
import com.develop_ping.union.party.exception.ParticipationNotFoundException;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

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
        ErrorResponse.FieldError fieldError = new ErrorResponse.FieldError(e.getPropertyName(), (String) e.getValue(), e.getMessage());
        return buildFieldErrors(ErrorCode.INPUT_VALUE_INVALID, List.of(fieldError));
    }

    @ExceptionHandler(GatheringValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ErrorResponse handleGatheringValidationException(GatheringValidationException e) {
        return buildError(ErrorCode.INPUT_VALUE_INVALID);
    }

    @ExceptionHandler(GatheringPermissionDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    protected ErrorResponse handleGatheringPermissionDeniedException(GatheringPermissionDeniedException e) {
        return buildError(ErrorCode.GATHERING_PERMISSION_DENIED);
    }

    @ExceptionHandler(AlreadyJoinedException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    protected ErrorResponse handleAlreadyJoinedException(AlreadyJoinedException e) {
        return buildError(ErrorCode.ALREADY_JOINED);
    }

    @ExceptionHandler(ParticipantLimitExceededException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    protected ErrorResponse handleParticipantLimitExceededException(ParticipantLimitExceededException e) {
        return buildError(ErrorCode.PARTICIPANT_LIMIT_EXCEEDED);
    }

    @ExceptionHandler(ParticipationNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected ErrorResponse handleParticipationNotFoundException(ParticipationNotFoundException e) {
        return buildError(ErrorCode.PARTICIPATION_NOT_FOUND);
    }

    @ExceptionHandler(OwnerCannotExitException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    protected ErrorResponse handleOwnerCannotExitException(OwnerCannotExitException e) {
        return buildError(ErrorCode.OWNER_CANNOT_EXIT);
    }

    @ExceptionHandler(RecruitmentAlreadyCompletedException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    protected ErrorResponse handleRecruitmentAlreadyCompletedException(RecruitmentAlreadyCompletedException e) {
        return buildError(ErrorCode.RECRUITMENT_ALREADY_COMPLETED);
    }

    @ExceptionHandler(NoMatchingResultsException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected ErrorResponse handleNoMatchingResultsException(NoMatchingResultsException e) {
        return buildError(ErrorCode.NO_MATCHING_RESULTS);
    }
}
