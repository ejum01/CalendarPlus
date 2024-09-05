package com.calendarplus.calendarplus.exception;

/**
 * 사용자 조회 시 사용자를 찾을 수 없는 경우 발생하는 예외 클래스입니다.
 * <p>
 * 사용자 이름이나 이메일로 사용자를 찾을 수 없을 때 발생합니다.
 * </p>
 *
 * @author : ejum
 * @since : 9/6/24
 */
public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String message) {
        super(message);
    }
}