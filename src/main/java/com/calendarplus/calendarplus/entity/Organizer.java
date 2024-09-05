package com.calendarplus.calendarplus.entity;

import jakarta.persistence.Embeddable;
import lombok.Getter;

/**
 * 주최자 정보를 담는 클래스입니다.
 * 주최자의 이메일과 이름을 포함합니다.
 *
 * @author : ejum
 * @since : 9/5/24
 */
@Embeddable
@Getter
public class Organizer {

    private String email;
    private String displayName;
}