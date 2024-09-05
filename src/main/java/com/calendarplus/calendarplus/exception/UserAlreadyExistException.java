package com.calendarplus.calendarplus.exception;

import com.calendarplus.calendarplus.common.Constans;

/**
 * 이미 존재하는 사용자에 대한 예외를 처리하는 클래스입니다.
 * <p>
 * 사용자 생성 시 이메일이 이미 존재하는 경우 발생합니다.
 * </p>
 *
 * @author : ejum
 * @since : 9/5/24
 */
public class UserAlreadyExistException extends RuntimeException {

    public UserAlreadyExistException() {
        super(Constans.USER_ALREADY_EXIST);
    }

}