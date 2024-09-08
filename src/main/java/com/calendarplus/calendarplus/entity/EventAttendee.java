package com.calendarplus.calendarplus.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

/**
 * Test 클래스 입니다.
 *
 * @author : ejum
 * @since : 9/8/24
 */
@Entity
@Setter
@Getter
public class EventAttendee extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    private String email;

    private Boolean partstat = false; // 참석 여부를 저장

    // getters and setters
}

