package com.calendarplus.calendarplus.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


/**
 * 참석자를 나타내는 엔티티 클래스입니다.
 * 참석자의 이메일, 이름, 참석 상태를 포함합니다.
 *
 * @author : ejum
 * @since : 9/5/24
 */
@Entity
@Table(name = "attendee")
@Getter
@SuperBuilder
@NoArgsConstructor
public class Attendee extends BaseEntity {
    private Long eventId;
    private String email;         // 참석자 이메일

}