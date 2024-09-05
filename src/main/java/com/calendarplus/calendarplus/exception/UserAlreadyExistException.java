package com.calendarplus.calendarplus.exception;

import com.calendarplus.calendarplus.common.Constans;

/**
 * 이미 존재하는 유저일 경우 에러메세지 발행.
 *
 * @author : ejum
 * @since : 9/5/24
 */
public class UserAlreadyExistException extends RuntimeException {

    public UserAlreadyExistException() {
        super(Constans.USER_ALREADY_EXIST);
    }

}
