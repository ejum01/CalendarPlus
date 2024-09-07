package com.calendarplus.calendarplus.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 오류 응답을 위한 클래스입니다.
 * <p>
 * 에러 메시지를 포함하며, 예외 처리 시 클라이언트에게 전달됩니다.
 * </p>
 *
 * @author : ejum
 * @since : 9/6/24
 */
@Getter
@Setter
public class ErrorResponse {
    private String message;

    public ErrorResponse(String message) {
        this.message = message;
    }
}