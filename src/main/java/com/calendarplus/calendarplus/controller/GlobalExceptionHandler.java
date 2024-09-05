package com.calendarplus.calendarplus.controller;

import com.calendarplus.calendarplus.exception.ErrorResponse;
import com.calendarplus.calendarplus.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 전역적으로 예외를 처리하는 클래스입니다.
 * <p>
 * 이 클래스는 애플리케이션 전역에서 발생하는 예외를 처리하여
 * 일관된 형식으로 오류 응답을 반환합니다.
 * </p>
 * <ul>
 *     <li>{@link UserNotFoundException} 예외를 처리하여 404 Not Found 응답을 반환합니다.</li>
 *     <li>기타 모든 예외를 처리하여 500 Internal Server Error 응답을 반환합니다.</li>
 * </ul>
 *
 * @author : ejum
 * @since : 9/6/24
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * {@link UserNotFoundException} 예외를 처리합니다.
     *
     * @param e 발생한 예외
     * @return 404 Not Found 응답과 예외 메시지를 포함한 {@link ErrorResponse} 객체
     */
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFoundException(UserNotFoundException e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    /**
     * 모든 기타 예외를 처리합니다.
     *
     * @param e 발생한 예외
     * @return 500 Internal Server Error 응답과 예외 메시지를 포함한 {@link ErrorResponse} 객체
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGlobalException(Exception e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }
}